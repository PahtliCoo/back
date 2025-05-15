package life.pahtlicoo.application.usecase.role;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import life.pahtlicoo.application.service.RoleService;
import life.pahtlicoo.domain.model.Role;

@ApplicationScoped
public class GetRoleUseCase {
    @Inject
    RoleService roleService;

    public Role execute(int roleId){
        return roleService.getRole(roleId);
    }
}
