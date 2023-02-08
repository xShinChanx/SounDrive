package app.buisness.impl;

import app.buisness.AccessTokenDecoder;
import app.buisness.GetUserBasedoOnAccessTokenUseCase;
import app.domain.AccessToken;
import app.domain.Request.GetUserBasedOnAccessTokenRequest;
import app.domain.Response.GetUserResponse;
import app.persistence.Entity.UserEntity;
import app.persistence.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class GetUserBasedOnAccessTokeUseCaseImpl implements GetUserBasedoOnAccessTokenUseCase {
    private UserRepository userRepository;
    private AccessTokenDecoder decoder;

    @Override
    public GetUserResponse getUser(GetUserBasedOnAccessTokenRequest request) {
        System.out.println("xxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxxx");
        System.out.println(request.getAccessToken());

        AccessToken accessToken = decoder.decode(request.getAccessToken());

        List<String> roles = decoder.decode(request.getAccessToken()).getRoles();
        String role = roles.get(0).toString();
        System.out.println(role);

        UserEntity user = userRepository.findUserById(accessToken.getUserId());

        return GetUserResponse.builder().name(user.getName()).id(user.getId()).email(user.getEmail()).role(role).build();
    }
}
