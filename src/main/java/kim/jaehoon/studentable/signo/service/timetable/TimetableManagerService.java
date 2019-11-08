package kim.jaehoon.studentable.signo.service.timetable;

import kim.jaehoon.studentable.signo.domain.payload.TokenResponse;
import reactor.core.publisher.Mono;

public interface TimetableManagerService {

    Mono<TokenResponse> signIn(String email, String password);

}
