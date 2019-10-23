package kim.jaehoon.studentable.signo.controller;

import kim.jaehoon.studentable.signo.domain.entity.Meal;
import kim.jaehoon.studentable.signo.service.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping(value = "/meal")
public class MealController {

    @Autowired
    MealService mealService;

    @GetMapping
    public Flux<Meal> getMeal(@RequestParam String date, @RequestParam("school_code") String schoolCode) {
        return mealService.findBySchoolAndDate(schoolCode, date);
    }
}
