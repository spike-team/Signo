package kim.jaehoon.studentable.signo.service.manager;

import kim.jaehoon.studentable.signo.domain.document.Manager;
import kim.jaehoon.studentable.signo.domain.payload.TokenResponse;
import kim.jaehoon.studentable.signo.domain.repository.EmailRepository;
import kim.jaehoon.studentable.signo.domain.repository.ManagerRepository;
import kim.jaehoon.studentable.signo.domain.repository.SchoolInfoRepository;
import kim.jaehoon.studentable.signo.exception.*;
import kim.jaehoon.studentable.signo.service.token.TokenService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.reactive.result.view.RedirectView;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Service
@AllArgsConstructor
public class ManagerServiceImpl implements ManagerService {

    private TokenService tokenService;
    private PasswordEncoder passwordEncoder;
    private ManagerRepository managerRepository;
    private EmailRepository emailRepository;
    private SchoolInfoRepository schoolInfoRepository;
    private ResourceLoader resourceLoader;

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

    @Override
    public Mono<TokenResponse> refresh(String identity) {
        return Mono.just(TokenResponse.containRefreshToken(tokenService.createAccessToken(identity)));
    }

}
