package life.pahtlicoo.application.usecase.role;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import life.pahtlicoo.application.service.RoleService;
import life.pahtlicoo.domain.model.Role;

import java.util.List;

@ApplicationScoped
public class GetAllRolesUseCase {
    @Inject
    RoleService roleService;

    public List<Role> execute() {
        return roleService.getAllRoles();
    }
}
