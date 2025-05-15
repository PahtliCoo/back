package life.pahtlicoo.application.usecase.role;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import life.pahtlicoo.application.service.RoleService;

@ApplicationScoped
public class UpdateRoleNameUseCase {
    @Inject
    RoleService roleService;

    public void execute(int roleId, String name) {
        roleService.updateRoleName(roleId, name);
    }
}
