package life.pahtlicoo.application.mapper;

import jakarta.enterprise.context.ApplicationScoped;
import life.pahtlicoo.application.dto.role.CreateRoleReqDTO;
import life.pahtlicoo.domain.model.Role;

@ApplicationScoped
public class RoleDomainMapper {
    public Role createRoleToDomain(CreateRoleReqDTO createRoleReqDTO){
        Role role = new Role();
        role.setName(createRoleReqDTO.getName());
        return role;
    }
}
