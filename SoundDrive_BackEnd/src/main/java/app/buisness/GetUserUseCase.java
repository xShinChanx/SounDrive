package app.buisness;

import app.domain.User;

import java.util.Optional;

public interface GetUserUseCase {

     User getUserBasedOnID(Long userID);
}
