package kim.jaehoon.studentable.signo.controller;

import kim.jaehoon.studentable.signo.domain.payload.Login;
import kim.jaehoon.studentable.signo.domain.payload.TokenResponse;
import kim.jaehoon.studentable.signo.service.timetable.TimetableManagerServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/manager")
public class TimetableManagerController {

    @Autowired
    TimetableManagerServiceImpl managerService;

    @PostMapping("/auth")
    public Mono<TokenResponse> signIn(@RequestBody Login login) {
        return managerService.signIn(login.getEmail(), login.getPassword());
    }
}
