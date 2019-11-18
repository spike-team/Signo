package kim.jaehoon.studentable.signo.service.timetable;

import kim.jaehoon.studentable.signo.domain.payload.TokenResponse;
import kim.jaehoon.studentable.signo.domain.repository.TimetableManagerRepository;
import kim.jaehoon.studentable.signo.exception.InvalidUserCredentialException;
import kim.jaehoon.studentable.signo.exception.UserNotFoundException;
import kim.jaehoon.studentable.signo.service.token.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class TimetableManagerServiceImpl implements TimetableManagerService {

    private TokenService tokenService;
    private PasswordEncoder passwordEncoder;
    private TimetableManagerRepository managerRepository;


    @Autowired
    public TimetableManagerServiceImpl(TokenService tokenService, TimetableManagerRepository managerRepository) {
        this.tokenService = tokenService;
        this.managerRepository = managerRepository;
        this.passwordEncoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    public Mono<TokenResponse> signIn(String email, String password) {
        return managerRepository.findByEmail(email)
                .switchIfEmpty(Mono.error(new UserNotFoundException()))
                .flatMap(user -> {
                    if (passwordEncoder.matches(password, user.getPassword())) {
                        return Mono.just(new TokenResponse(tokenService.createAccessToken(email), tokenService.createRefreshToken(email)));
                    } else {
                        return Mono.error(new InvalidUserCredentialException());
                    }
                });
    }

    public Mono<TokenResponse> refresh(String identity) {
        return Mono.just(TokenResponse.containRefreshToken(tokenService.createAccessToken(identity)));
    }
}
