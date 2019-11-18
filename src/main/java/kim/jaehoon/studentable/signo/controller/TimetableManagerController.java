package kim.jaehoon.studentable.signo.controller;

import kim.jaehoon.studentable.signo.domain.payload.Login;
import kim.jaehoon.studentable.signo.domain.payload.TokenResponse;
import kim.jaehoon.studentable.signo.service.timetable.TimetableManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1")
public class TimetableManagerController {

    @Autowired
    TimetableManagerService managerService;

    @PostMapping("/manager/auth")
    public Mono<TokenResponse> signIn(@RequestBody Login login) {
        return managerService.signIn(login.getEmail(), login.getPassword());
    }

    @PatchMapping("/manager/auth")
    public Mono<TokenResponse> refresh(@RequestHeader("X-Refresh-Token") String refresh) {
        return managerService.refresh(refresh);
    }

}
