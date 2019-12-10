package kim.jaehoon.studentable.signo.service.timetable;

import kim.jaehoon.studentable.signo.domain.entity.Timetable;
import kim.jaehoon.studentable.signo.domain.repository.ManagerRepository;
import kim.jaehoon.studentable.signo.domain.repository.SchoolInfoRepository;
import kim.jaehoon.studentable.signo.domain.repository.TimetableRepository;
import kim.jaehoon.studentable.signo.exception.TimetableNotFoundException;
import kim.jaehoon.studentable.signo.exception.error.FileError;
import kim.jaehoon.studentable.signo.exception.manager.ManagerNotFoundException;
import kim.jaehoon.studentable.signo.exception.school.SchoolNotFoundException;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ZeroCopyHttpOutputMessage;
import org.springframework.http.codec.multipart.FilePart;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TimetableServiceImpl implements TimetableService {

    private String fileName;

    private ManagerRepository managerRepository;
    private TimetableRepository timetableRepository;
    private SchoolInfoRepository schoolInfoRepository;

    @Autowired
    public TimetableServiceImpl(ManagerRepository managerRepository, TimetableRepository timetableRepository, SchoolInfoRepository schoolInfoRepository) {
        this.managerRepository = managerRepository;
        this.timetableRepository = timetableRepository;
        this.schoolInfoRepository = schoolInfoRepository;
    }

    @Override
    public Mono<Timetable> findBySchoolCodeAndSchoolClass(String schoolCode, String schoolClass) {
        return timetableRepository.findBySchoolCodeAndSchoolClass(schoolCode, schoolClass)
                .switchIfEmpty(Mono.error(TimetableNotFoundException::new));
    }

    @Override
    public Flux<Timetable> findAll() {
        return timetableRepository.findAll();
    }

    @Override
    public Mono<Timetable> importExcelSheetForSingleTimetable(String email, String schoolClass, FilePart filePart) {
        return convertToFile(filePart).flatMap(file -> {
            if (isFileExtensionCorrect(file.getName()))
                return Mono.fromCallable(() -> getWorkBook(file))
                        .flatMap(workbook -> updateSingleSubjects(email, schoolClass, toSingleSubjects(workbook)));

            return Mono.error(new FileError("File error occurred"));
        });
    }

    @Override
    public Flux importExcelSheetForMultipleTimetable(String email, FilePart filePart) {
        return convertToFile(filePart).flatMapMany(file -> {
            if (isFileExtensionCorrect(file.getName()))
                return Mono.fromCallable(() -> getWorkBook(file))
                        .flatMapMany(workbook -> updateMultipleSubjects(email, toMultipleSubjects(workbook)));

            return Mono.error(new FileError("File error occurred"));
        });
    }

    @Override
    public Mono generateExcelSheetForSingleTimetable(ServerHttpResponse response, String schoolCode, String schoolClass) {
        return schoolInfoRepository.findBySchoolCode(schoolCode)
                .switchIfEmpty(Mono.error(SchoolNotFoundException::new))
                .flatMap(schoolInfo -> timetableRepository.findBySchoolCodeAndSchoolClass(schoolCode, schoolClass)
                        .switchIfEmpty(Mono.error(TimetableNotFoundException::new))
                        .flatMap(timetable -> Mono.fromCallable(() -> {
                            this.fileName = schoolCode + "_" + schoolClass + ".xlsx";
                            File file = new File(fileName);
                            Workbook workbook = generateSingleTimetableWorkbook(timetable.getSubjects());
                            workbook.write(new FileOutputStream(file));
                            return file;
                        }).flatMap(file -> generateResponse(response, file, fileName))));
    }

    @Override
    public Mono generateExcelSheetForMultipleTimetable(ServerHttpResponse response, String schoolCode) throws IOException {
        ClassPathResource resource = new ClassPathResource("all_sheet.xlsx");
        Workbook workbook = new XSSFWorkbook(resource.getInputStream());
        Sheet sheet = workbook.getSheetAt(0);
        this.fileName = "전체학반시간표_" + schoolCode + ".xlsx";
        List<Timetable> timetables = new ArrayList<>();


        return schoolInfoRepository.findBySchoolCode(schoolCode)
                .switchIfEmpty(Mono.error(SchoolNotFoundException::new))
                .flatMap(schoolInfo -> timetableRepository.findAllBySchoolCodeOrderBySchoolClass(schoolCode)
                        .switchIfEmpty(Mono.error(TimetableNotFoundException::new))
                        .flatMap(timetable -> {
                            timetables.add(timetable);
                            return Flux.just(timetable);
                        })
                        .then(Mono.fromCallable(() -> {
                            File file = new File(fileName);
                            writeToSheet(sheet, timetables);
                            workbook.write(new FileOutputStream(file));
                            return file;
                        })).flatMap(file -> generateResponse(response, file, fileName)));
    }

    private void writeToSheet(Sheet sheet, List<Timetable> timetables) {
        for (int r = 4; r <= sheet.getLastRowNum(); r++) {
            Row row = sheet.getRow(r);
            Timetable table = timetables.get(r - 4);

            List<String> modifiedSubjects = new ArrayList<>();
            for (int i = 0; i < table.getSubjects().size(); i++)
                modifiedSubjects.add(table.getSubjects().get(i).replace("/", "\n"));
            table.setSubjects(modifiedSubjects);

            for (int c = 0; c <= row.getLastCellNum() - 1; c++) {
                if (c == 0)
                    row.getCell(0).setCellValue(table.getSchoolClass());
                else
                    row.getCell(c).setCellValue(table.getSubjects().get(c - 1));
            }
        }
    }

    private Mono generateResponse(ServerHttpResponse response, File file, String fileName) {
        try {
            HttpHeaders headers = response.getHeaders();
            headers.set(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename="
                    .concat(URLEncoder.encode(fileName, StandardCharsets.UTF_8.displayName())));
            ZeroCopyHttpOutputMessage zeroCopyHttpOutputMessage = (ZeroCopyHttpOutputMessage) response;
            return zeroCopyHttpOutputMessage.writeWith(file, 0, file.length());
        } catch (UnsupportedEncodingException e) {
            return Mono.error(new FileError(e.getMessage()));
        }
    }

    private Mono<Timetable> updateSingleSubjects(String email, String schoolClass, List<String> subjects) {
        return managerRepository.findByEmail(email)
                .switchIfEmpty(Mono.error(ManagerNotFoundException::new))
                .flatMap(manager -> timetableRepository.findBySchoolCodeAndSchoolClass(manager.getSchoolCode(), schoolClass)
                        .switchIfEmpty(timetableRepository.save(
                                new Timetable(new ObjectId().toString(), manager.getSchoolCode(), schoolClass, subjects)))
                        .flatMap(timetable -> {
                            timetable.setSubjects(subjects);
                            return timetableRepository.save(timetable);
                        }));
    }

    private Flux updateMultipleSubjects(String email, Map<String, List<String>> multipleSubjects) {
        return managerRepository.findByEmail(email)
                .switchIfEmpty(Mono.error(ManagerNotFoundException::new))
                .flatMapMany(manager -> timetableRepository.deleteAll(timetableRepository.findAllBySchoolCode(manager.getSchoolCode()))
                        .thenMany(timetableRepository.saveAll(toList(manager.getSchoolCode(), multipleSubjects))));
    }

    private List<Timetable> toList(String schoolCode, Map<String, List<String>> multipleSubjects) {
        List<Timetable> timetables = new ArrayList<>();
        for (String schoolClass : multipleSubjects.keySet()) {
            timetables.add(new Timetable(new ObjectId().toString(), schoolCode, schoolClass, multipleSubjects.get(schoolClass)));
        }
        return timetables;
    }

    private List<String> toSingleSubjects(Workbook workbook) {
        List<String> subjects = new ArrayList<>();
        Sheet sheet = workbook.getSheetAt(0);

        for (int c = 1; c <= 5; c++) {
            for (int r = 1; r <= 7; r++) {
                Row row = sheet.getRow(r);
                subjects.add(row.getCell(c).getStringCellValue());
            }
        }

        return subjects;
    }

    private Map<String, List<String>> toMultipleSubjects(Workbook workbook) {
        Map<String, List<String>> multipleSubjects = new HashMap<>();
        Sheet sheet = workbook.getSheetAt(0);

        for (int r = 4; r <= sheet.getLastRowNum(); r++) {
            Row row = sheet.getRow(r);
            List<String> subjects = _fromRow(row);
            multipleSubjects.put(row.getCell(0).getStringCellValue(), subjects);
        }

        return multipleSubjects;
    }

    private List<String> _fromRow(Row row) {
        List<String> subjects = new ArrayList<>();

        for (int c = 1; c < row.getLastCellNum(); c++) {
            String subject = row.getCell(c).getStringCellValue();

            if (isSpecialSymbol(subject))
                subjects.add(subjects.get(subjects.size() - 1));
            else
                subjects.add(subject.replaceFirst(" ", "/").replace(" ", ""));
        }
        return subjects;
    }

    private boolean isSpecialSymbol(String subject) {
        return subject.equals("─▷") || subject.equals("──");
    }

    private XSSFWorkbook generateSingleTimetableWorkbook(List<String> subjects) throws IOException {
        ClassPathResource resource = new ClassPathResource("neis_sheet.xlsx");
        XSSFWorkbook workbook = new XSSFWorkbook(resource.getInputStream());
        writeSingleSubjectsToSheet(workbook.getSheetAt(0), subjects);
        return workbook;
    }

    private void writeSingleSubjectsToSheet(Sheet sheet, List<String> subjects) {
        for (int c = 1; c <= 5; c++) {
            for (int r = 1; r <= 7; r++) {
                Row row = sheet.getRow(r);
                if ((c - 1) * 7 + r - 1 == 34) {
                    break;
                }
                row.getCell(c).setCellValue(subjects.get((c - 1) * 7 + r - 1));
            }
        }
    }

    private Flux<Timetable> writeMultipleSubjectsToSheet(Sheet sheet, Timetable timetable) {
        for (int r = 4; r <= sheet.getLastRowNum(); r++) {
            Row row = sheet.getRow(r);
            for (int c = 0; c <= row.getLastCellNum() - 1; c++) {
                if (c == 0)
                    row.getCell(0).setCellValue(timetable.getSchoolClass());
                else
                    row.getCell(c).setCellValue(timetable.getSubjects().get(c - 1));
            }
        }
        return Flux.just(timetable);
    }

    private Workbook getWorkBook(File file) throws IOException {
        FileInputStream fileInputStream = new FileInputStream(file);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        IOUtils.copy(fileInputStream, byteArrayOutputStream);

        try {
            return new HSSFWorkbook(new POIFSFileSystem(file));
        } catch (Exception e) {
            return new XSSFWorkbook(new ByteArrayInputStream(byteArrayOutputStream.toByteArray()));
        }
    }

    private Mono<File> convertToFile(FilePart filePart) {
        return Mono.fromCallable(() -> {
            Path path = Files.createTempFile(null, filePart.filename());
            File file = path.toFile();
            filePart.transferTo(file);
            return file;
        });
    }

    private boolean isFileExtensionCorrect(String filename) {
        return StringUtils.endsWithIgnoreCase(filename, "xls") || StringUtils.endsWithIgnoreCase(filename, "xlsx");
    }

}
