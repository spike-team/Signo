package kim.jaehoon.studentable.signo.handler;

import kim.jaehoon.studentable.signo.domain.payload.ApiResponse;
import kim.jaehoon.studentable.signo.domain.payload.LoginRequest;
import kim.jaehoon.studentable.signo.domain.payload.LoginResponse;
import kim.jaehoon.studentable.signo.domain.repository.TimetableManagerRepository;
import kim.jaehoon.studentable.signo.exception.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

public class AuthHandler {

    private TimetableManagerRepository timetableManagerRepository;

    public AuthHandler(TimetableManagerRepository timetableManagerRepository) {
        this.timetableManagerRepository = timetableManagerRepository;
    }

    @Autowired
    private PasswordEncoder passwordEncoder;

    public Mono login(ServerRequest request) {
        Mono<LoginRequest> loginRequest = request.bodyToMono(LoginRequest.class);
        return loginRequest.flatMap(login -> timetableManagerRepository.findByEmail(login.getEmail())
            .flatMap(timetableManager -> {
                if (passwordEncoder.matches(login.getPassword(), timetableManager.getPassword())) {
                    return ServerResponse.ok()
                            .contentType(MediaType.APPLICATION_JSON_UTF8)
                            .body(BodyInserters.fromObject(new LoginResponse()));
                } else {
                    throw new BadRequestException("Invalid credentials");
                }
            }).switchIfEmpty(
                ServerResponse.badRequest()
                    .body(BodyInserters.fromObject(new ApiResponse(false, "User doesn't exist")))
                ));
    }
}
