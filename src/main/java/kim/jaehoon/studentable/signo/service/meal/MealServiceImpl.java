package kim.jaehoon.studentable.signo.service.meal;

import kim.jaehoon.studentable.signo.domain.entity.Meal;
import kim.jaehoon.studentable.signo.domain.entity.SchoolInfo;
import kim.jaehoon.studentable.signo.domain.payload.Menu;
import kim.jaehoon.studentable.signo.domain.repository.MealRepository;
import kim.jaehoon.studentable.signo.domain.repository.SchoolInfoRepository;
import kim.jaehoon.studentable.signo.exception.school.SchoolError;
import kim.jaehoon.studentable.signo.exception.school.SchoolNotFoundException;
import kr.go.neis.api.School;
import kr.go.neis.api.SchoolException;
import kr.go.neis.api.SchoolMenu;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static kim.jaehoon.studentable.signo.util.RegionConverter.toEnum;

@Service
public class MealServiceImpl implements MealService {

    @Autowired
    private SchoolInfoRepository schoolRepository;

    @Autowired
    private MealRepository mealRepository;

    @Override
    public Mono<Meal> findBySchoolAndDate(String schoolCode, LocalDate date) {

        return schoolRepository.findBySchoolCode(schoolCode)
                .switchIfEmpty(Mono.error(new SchoolNotFoundException()))
                .flatMap(schoolInfo -> mealRepository.findBySchoolCodeAndDate(schoolCode, date.toString())
                        .switchIfEmpty(cashingMeal(schoolCode, schoolInfo, date)));
    }

    private Mono<Meal> cashingMeal(String schoolCode, SchoolInfo schoolInfo, LocalDate date) {
        try {
            List<SchoolMenu> responses = School.find(toEnum(schoolInfo.getFullName()), schoolInfo.getName())
                    .getMonthlyMenu(date.getYear(), date.getMonthValue());

            List<Menu> willCashed = new ArrayList<>();

            for (SchoolMenu menu : responses) {
                willCashed.add(new Menu(menu.breakfast, menu.lunch, menu.dinner));
            }

            Meal meal = new Meal(new ObjectId(), schoolCode, date.toString(), willCashed);
            return mealRepository.save(meal).then(Mono.just(meal));
        } catch (SchoolException exception) {
            throw new SchoolError(exception.getMessage());
        }
    }
}
