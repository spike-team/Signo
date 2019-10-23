package kim.jaehoon.studentable.signo.config;

import kim.jaehoon.studentable.signo.domain.repository.TeacherRepository;
import kim.jaehoon.studentable.signo.domain.repository.TimetableManagerRepository;
import kim.jaehoon.studentable.signo.handler.AuthHandler;
import kim.jaehoon.studentable.signo.handler.TeacherHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class RouterConfig {

    @Autowired
    private TeacherRepository teacherRepository;

    @Autowired
    private TimetableManagerRepository timetableManagerRepository;

    @Bean
    public RouterFunction<ServerResponse> teacherRoute() {
        TeacherHandler teacherHandler = new TeacherHandler(teacherRepository);
        return RouterFunctions
                .route(POST("/api/v1/teacher").and(contentType(APPLICATION_JSON_UTF8)), teacherHandler::createTeacher);
    }

    @Bean
    public RouterFunction authRoute() {
        AuthHandler authHandler = new AuthHandler(timetableManagerRepository);
        return RouterFunctions
                .route(POST("api/v1/auth").and(accept(APPLICATION_JSON_UTF8)), authHandler::login);
    }
}
