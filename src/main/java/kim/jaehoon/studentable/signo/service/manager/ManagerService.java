package kim.jaehoon.studentable.signo.service.manager;

import kim.jaehoon.studentable.signo.domain.document.Manager;
import kim.jaehoon.studentable.signo.domain.payload.TokenResponse;
import reactor.core.publisher.Mono;

public interface ManagerService {

    Mono signUp(Manager manager);

    Mono verify(String code);

    Mono<TokenResponse> signIn(String email, String password);

    Mono<TokenResponse> refresh(String identity);

}
