package life.pahtlicoo.application.usecase.role;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import life.pahtlicoo.application.service.RoleService;

@ApplicationScoped
public class DeleteRoleUseCase {
    @Inject
    RoleService roleService;

    public void execute(int roleId) {
        roleService.deleteRole(roleId);
    }
}

//TODO este seguro tiene más logica, tipo si se borra, los usuarios deben tener otro rol
