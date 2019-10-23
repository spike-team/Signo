package kim.jaehoon.studentable.signo.service;

import kim.jaehoon.studentable.signo.domain.entity.Meal;
import kim.jaehoon.studentable.signo.domain.repository.MealRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class MealService implements IMealService {

    @Autowired
    MealRepository mealRepository;

    @Override
    public void create(Meal meal) {
        mealRepository.save(meal).subscribe();
    }

    @Override
    public Flux<Meal> findBySchoolAndDate(String schoolCode, String date) {
        return mealRepository.findBySchoolCodeAndDate(schoolCode, date);
    }

    @Override
    public Flux<Meal> findAll() {
        return mealRepository.findAll();
    }
}
