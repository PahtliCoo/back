package life.pahtlicoo.application.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import life.pahtlicoo.domain.model.Role;
import life.pahtlicoo.domain.repository.RoleRepository;

import java.util.List;

@ApplicationScoped
public class RoleService {
    @Inject
    RoleRepository roleRepository;

    public void createRole(Role role) {
        roleRepository.createRole(role);
    }

    public Role getRole(int roleId) {
        return roleRepository.getRole(roleId);
    }

    public List<Role> getAllRoles() {
        return roleRepository.getAllRoles();
    }

    public void updateRoleName(int roleId, String name){
        roleRepository.updateRoleName(roleId, name);
    }

    public void deleteRole(int roleId) {
        roleRepository.deleteRole(roleId);
    }
}
