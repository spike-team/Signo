package kim.jaehoon.studentable.signo.controller;

import kim.jaehoon.studentable.signo.domain.document.Meal;
import kim.jaehoon.studentable.signo.exception.BadRequestException;
import kim.jaehoon.studentable.signo.service.meal.MealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

@RestController
@RequestMapping(value = "/api/v1")
public class MealController {

    @Autowired
    MealService mealService;

    @GetMapping("/meal")
    public Mono<Meal> getMeal(@RequestParam String date, @RequestParam("school_code") String schoolCode) {

        try {
            return mealService.findBySchoolAndDate(schoolCode, LocalDate.parse(date).withDayOfMonth(1));

        } catch (DateTimeParseException exception) {
            throw new BadRequestException(exception.getMessage());

        }

    }
}
