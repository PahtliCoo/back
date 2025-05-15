package life.pahtlicoo.infrastructure.mapper;

import jakarta.enterprise.context.ApplicationScoped;
import life.pahtlicoo.domain.model.Role;
import life.pahtlicoo.infrastructure.entity.RoleEntity;

@ApplicationScoped
public class RoleEntityMapper {
    public Role toDomain(RoleEntity roleEntity){
        return new Role(roleEntity.getRoleId(), roleEntity.getName(), roleEntity.getCreatedAt(), roleEntity.getUpdatedAt());
    }

    public RoleEntity toEntity(Role role){
        return new RoleEntity(role.getRoleId(), role.getName(), role.getCreatedAt(), role.getUpdatedAt());
    }


}
