package life.pahtlicoo.infrastructure.mapper;

import life.pahtlicoo.domain.model.User;
import life.pahtlicoo.infrastructure.entity.UserEntity;

public class UserMapper {
    public static User toDomain(UserEntity userEntity){
        User user = new User();
        // userId
        user.setUserId(userEntity.getId());
        // firebaseId
        user.setFirebaseId(userEntity.getFirebaseId());
        // email
        user.setEmail(userEntity.getEmail());
        // name
        user.setName(userEntity.getName());
        //lastName
        user.setLastName(userEntity.getLastName());
        // siteId
        user.setSiteId(userEntity.getSiteId());
        //roleId
        user.setRoleId(userEntity.getRoleId());
        //createdAt
        user.setCreatedAt(userEntity.getCreatedAt());
        //updatedAt
        user.setUpdatedAt(userEntity.getUpdatedAt());
        return user;
    };

    public static UserEntity toEntity(User user){
        UserEntity userEntity = new UserEntity();
        // userId
        userEntity.setId(user.getUserId());
        // firebaseId
        userEntity.setFirebaseId(user.getFirebaseId());
        // email
        userEntity.setEmail(user.getEmail());
        // name
        userEntity.setName(user.getName());
        //lastName
       userEntity.setLastName(user.getLastName());
        // siteId
        userEntity.setSiteId(user.getSiteId());
        //roleId
        userEntity.setRoleId(user.getRoleId());
        //createdAt
        userEntity.setCreatedAt(user.getCreatedAt());
        //updatedAt
        userEntity.setUpdatedAt(user.getUpdatedAt());
        return userEntity;
    };
}
