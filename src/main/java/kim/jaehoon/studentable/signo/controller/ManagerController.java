package kim.jaehoon.studentable.signo.controller;

import kim.jaehoon.studentable.signo.domain.payload.Login;
import kim.jaehoon.studentable.signo.domain.payload.SignUpForm;
import kim.jaehoon.studentable.signo.domain.payload.TokenResponse;
import kim.jaehoon.studentable.signo.service.manager.ManagerService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1")
public class ManagerController {

    private ManagerService managerService;

    public ManagerController(ManagerService managerService) {
        this.managerService = managerService;
    }

    @PostMapping("/manager/signup")
    public Mono signUp(@RequestBody SignUpForm signUpForm) {
        return managerService.signUp(signUpForm.toManager());
    }

    @GetMapping(value = "/manager/verify")
    public Mono verify(@RequestParam("code") String code, ServerWebExchange exchange) {
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(HttpStatus.SEE_OTHER);
        response.getHeaders().add(HttpHeaders.LOCATION, "http://studentable.jaehoon.kim");
        return managerService.verify(code)
                .then(response.setComplete());
    }

    @PostMapping("/manager/auth")
    public Mono<TokenResponse> signIn(@RequestBody Login login) {
        return managerService.signIn(login.getEmail(), login.getPassword());
    }

    @PatchMapping("/manager/auth")
    public Mono<TokenResponse> refresh(@RequestHeader("X-Refresh-Token") String refresh) {
        return managerService.refresh(refresh);
    }

}
