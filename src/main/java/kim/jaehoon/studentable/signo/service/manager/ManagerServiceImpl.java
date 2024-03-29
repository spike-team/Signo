package kim.jaehoon.studentable.signo.service.manager;

import kim.jaehoon.studentable.signo.domain.entity.Manager;
import kim.jaehoon.studentable.signo.domain.payload.TokenResponse;
import kim.jaehoon.studentable.signo.domain.repository.EmailRepository;
import kim.jaehoon.studentable.signo.domain.repository.ManagerRepository;
import kim.jaehoon.studentable.signo.domain.repository.SchoolInfoRepository;
import kim.jaehoon.studentable.signo.exception.manager.InvalidUserCredentialException;
import kim.jaehoon.studentable.signo.exception.manager.InvalidVerificationCodeException;
import kim.jaehoon.studentable.signo.exception.manager.ManagerAlreadyExistsException;
import kim.jaehoon.studentable.signo.exception.manager.ManagerNotFoundException;
import kim.jaehoon.studentable.signo.exception.school.SchoolNotFoundException;
import kim.jaehoon.studentable.signo.exception.student.UserNotFoundException;
import kim.jaehoon.studentable.signo.service.token.TokenService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Service
@AllArgsConstructor
public class ManagerServiceImpl implements ManagerService {

    private TokenService tokenService;
    private ManagerRepository managerRepository;
    private EmailRepository emailRepository;
    private SchoolInfoRepository schoolInfoRepository;

    @Override
    public Mono signUp(Manager manager) {
        return managerRepository.findByEmail(manager.getEmail())
            .handle((saved, sink) -> sink.error(new ManagerAlreadyExistsException()))
            .switchIfEmpty(schoolInfoRepository.findBySchoolCode(manager.getSchoolCode())
                .switchIfEmpty(Mono.error(new SchoolNotFoundException()))
                .flatMap(schoolInfo -> {
                    try {
                        manager.generateVerificationCode();
                        ClassPathResource classPathResource = new ClassPathResource("verify_email.html");
                        byte[] bdata = FileCopyUtils.copyToByteArray(classPathResource.getInputStream());
                        String template = new String(bdata, StandardCharsets.UTF_8)
                            .replace("{{action_url}}",
                                "http://studentable.jaehoon.kim/api/v1/manager/verify?code="
                                            + manager.getVerificationCode());
                        emailRepository.sendEmail(manager.getEmail(),
                                "[Studentable] 이메일 주소를 인증해 주십시오.", template);
                        return managerRepository.save(manager);
                    } catch (IOException e) {
                        return Mono.error(e);
                    }
            }));
    }

    @Override
    public Mono verify(String code) {
        return managerRepository.findByVerificationCode(code)
            .switchIfEmpty(Mono.error(new InvalidVerificationCodeException()))
            .flatMap(manager -> {
                manager.setEmailVerified(true);
                manager.setVerificationCode(null);
                return managerRepository.save(manager);
            });
    }

    @Override
    public Mono<TokenResponse> signIn(String email, String password) {
        PasswordEncoder encoder = PasswordEncoderFactories.createDelegatingPasswordEncoder();
        return managerRepository.findByEmailAndEmailVerified(email, true)
            .switchIfEmpty(Mono.error(new UserNotFoundException()))
                .flatMap(user -> {
                    if (encoder.matches(password, user.getPassword())) {
                        return Mono.just(new TokenResponse(tokenService.createAccessToken(email)));
                    } else {
                        return Mono.error(new InvalidUserCredentialException());
                    }
                });
    }

    @Override
    public Mono<Manager> findByEmail(String email) {
        return managerRepository.findByEmail(email)
                .switchIfEmpty(Mono.error(ManagerNotFoundException::new));
    }

}
