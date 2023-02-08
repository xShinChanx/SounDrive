package app.buisness.impl;

import app.buisness.exception.InvalidAccessTokenException;
import app.buisness.impl.AccessTokenEncoderDecoderImpl;
import app.domain.AccessToken;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AccessTokenEncoderDecoderTest {
    AccessTokenEncoderDecoderImpl accessTokenEncoderDecoder;
    @Mock
    AccessToken accessToken;
    @BeforeEach
    void setUp() {
        accessTokenEncoderDecoder = new AccessTokenEncoderDecoderImpl("E91E158E4C6656F68B1B5D1C316766DE98D2AD6EF3BFB44F78E9CFCDF5");
    }

    @Test
    void encodeSuccess() {
        String subject = "subject";
        List<String> roles = Arrays.asList("admin", "user");
        Long userId = 1l;
        when(accessToken.getSubject()).thenReturn(subject);
        when(accessToken.getRoles()).thenReturn(roles);
        when(accessToken.getUserId()).thenReturn(userId);

        String accessTokenEncoded = accessTokenEncoderDecoder.encode(accessToken);

        assertNotNull(accessTokenEncoded);
    }

    @Test
    void decodeSuccess() {
        AccessToken expectedAccessToken = AccessToken.builder()
                .subject("subject")
                .roles(Arrays.asList("admin", "user"))
                .userId(1l)
                .build();
        String accessTokenEncoded = accessTokenEncoderDecoder.encode(expectedAccessToken);
        AccessToken actualAccessToken = accessTokenEncoderDecoder.decode(accessTokenEncoded);

        assertEquals(expectedAccessToken.getSubject(), actualAccessToken.getSubject());
        assertEquals(expectedAccessToken.getRoles(), actualAccessToken.getRoles());
        assertEquals(expectedAccessToken.getUserId(), actualAccessToken.getUserId());
    }

    @Test
    void decodeFail() {
        String accessTokenEncoded = "invalidToken";

        assertThrows(InvalidAccessTokenException.class, () -> accessTokenEncoderDecoder.decode(accessTokenEncoded));
    }
}