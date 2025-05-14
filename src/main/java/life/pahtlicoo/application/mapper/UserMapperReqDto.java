package life.pahtlicoo.application.mapper;

import life.pahtlicoo.application.dto.user.CreateUserReqDTO;
import life.pahtlicoo.domain.model.User;

public class UserMapperReqDto {
    public static User toDomain(CreateUserReqDTO createUserReqDTO){
        User user = new User();
        user.setName(createUserReqDTO.getName());
        user.setEmail(createUserReqDTO.getEmail());
        user.setLastName(createUserReqDTO.getLastName());
        user.setSiteId(createUserReqDTO.getSiteId());
        user.setRoleId(createUserReqDTO.getRoleId());
        return user;
    }
}
