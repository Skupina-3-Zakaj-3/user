package si.fri.rso.skupina3.user.models.converters;

import si.fri.rso.skupina3.lib.User;
import si.fri.rso.skupina3.user.models.entities.UserEntity;

public class UserConverter {
    public static User toDto(UserEntity entity) {

        User dto = new User();
        dto.setUserId(entity.getId());
        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        dto.setGoogleId(entity.getGoogleId());
        dto.setEmail(entity.getEmail());

        return dto;

    }

    public static UserEntity toEntity(User dto) {

        UserEntity entity = new UserEntity();
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setEmail(dto.getEmail());
        entity.setGoogleId(dto.getGoogleId());

        return entity;

    }
}
