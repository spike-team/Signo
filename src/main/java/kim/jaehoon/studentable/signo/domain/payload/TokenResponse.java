package kim.jaehoon.studentable.signo.domain.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class TokenResponse {
    private String accessToken;
    private String refreshToken;

    public static TokenResponse containAccessToken(String accessToken) {
        return new TokenResponse(accessToken, null);
    }

    public static TokenResponse containRefreshToken(String refreshToken) {
        return new TokenResponse(null, refreshToken);
    }
}
