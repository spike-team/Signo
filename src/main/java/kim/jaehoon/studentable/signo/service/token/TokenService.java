package kim.jaehoon.studentable.signo.service.token;

public interface TokenService {

    String createAccessToken(String identity);

    String createRefreshToken(String identity);

    String getIdentity(String jwt);

    String _generateToken(String identity, Long expireSeconds);

}
