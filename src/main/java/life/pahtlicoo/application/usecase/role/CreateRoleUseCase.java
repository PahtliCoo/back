package life.pahtlicoo.application.usecase.role;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import life.pahtlicoo.application.dto.role.CreateRoleReqDTO;
import life.pahtlicoo.application.mapper.RoleDomainMapper;
import life.pahtlicoo.application.service.RoleService;
import life.pahtlicoo.domain.model.Role;

@ApplicationScoped
public class CreateRoleUseCase {
    @Inject
    RoleService roleService;

    @Inject
    RoleDomainMapper roleDomainMapper;

    public void execute(CreateRoleReqDTO createRoleReqDTO) {
        Role role = roleDomainMapper.createRoleToDomain(createRoleReqDTO);
        roleService.createRole(role);
    }
}
