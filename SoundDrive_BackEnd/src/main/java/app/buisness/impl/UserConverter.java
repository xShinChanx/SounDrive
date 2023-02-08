package app.buisness.impl;

import app.domain.User;
import app.persistence.Entity.UserEntity;


public final class UserConverter {

    public static User convert(UserEntity user){

        return User.builder()
                .name(user.getName())
                .email(user.getEmail())
                .password(user.getPassword())
                .id(user.getId())
                .build();
    }
}
