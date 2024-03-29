package kim.jaehoon.studentable.signo.service.token;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException;
import kim.jaehoon.studentable.signo.exception.manager.InvalidTokenSignatureException;
import kim.jaehoon.studentable.signo.exception.error.TokenError;
import kim.jaehoon.studentable.signo.exception.TokenExpiredException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.time.Instant;
import java.util.Date;

@Service
public class TokenServiceImpl implements TokenService {

    @Value("${JWT_ACCESS_EXP}")
    private Long accessExp;

    @Value("${JWT_REFRESH_EXP}")
    private Long refreshExp;

    private SecretKey secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    public String getIdentity(String jwt) {
        try {
            Claims parsed = Jwts.parser()
                    .setSigningKey(secretKey)
                    .parseClaimsJws(jwt)
                    .getBody();
            if (parsed.getExpiration().before(Date.from(Instant.now()))) {
                throw new TokenExpiredException("Token has expired");
            }
            return parsed.getSubject();
        } catch (SignatureException e) {
            throw new InvalidTokenSignatureException();
        } catch (Exception e) {
            throw new TokenError(e.getMessage());
        }
    }

    public String createAccessToken(String identity) {
        return _generateToken(identity, accessExp);
    }

    public String createRefreshToken(String identity) {
        return _generateToken(identity, refreshExp);
    }

    public String _generateToken(String identity, Long expSeconds) {
        return Jwts.builder()
                .setSubject(identity)
                .setIssuedAt(java.sql.Date.from(Instant.now()))
                .setExpiration(java.sql.Date.from(Instant.now().plusSeconds(expSeconds)))
                .signWith(secretKey, SignatureAlgorithm.HS256)
                .compact();
    }

}
