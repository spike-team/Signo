package kim.jaehoon.studentable.signo.service.meal;

import kim.jaehoon.studentable.signo.domain.payload.Menu;
import kim.jaehoon.studentable.signo.domain.repository.SchoolInfoRepository;
import kim.jaehoon.studentable.signo.exception.SchoolError;
import kr.go.neis.api.School;
import kr.go.neis.api.SchoolException;
import kr.go.neis.api.SchoolMenu;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

import static kim.jaehoon.studentable.signo.util.RegionConverter.toEnum;

@Service
public class MealServiceImpl implements MealService {

    @Autowired
    SchoolInfoRepository schoolRepository;

    @Override
    public Mono<Menu> findBySchoolAndDate(String schoolCode, LocalDate date) {
       return schoolRepository.findBySchoolCode(schoolCode)
                .flatMap(schoolInfo1 -> {
                    try {
                        School school = School.find(toEnum(schoolInfo1.getFullName()), schoolInfo1.getName());

                        SchoolMenu menu = school
                                .getMonthlyMenu(date.getYear(), date.getMonthValue())
                                .get(date.getDayOfMonth() - 1);

                        return Mono.just(new Menu().fromSchoolMenu(menu));
                    } catch (SchoolException exception) {
                        throw new SchoolError(exception.getMessage());
                    }
                });
    }
}
