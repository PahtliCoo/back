package life.pahtlicoo.domain.repository;

import life.pahtlicoo.domain.model.Role;

import java.util.List;

public interface RoleRepository {
    public void createRole(Role role);
    public Role getRole(int roleId);
    public List<Role> getAllRoles();
    public void updateRoleName(int roleId, String name);
    public void deleteRole(int roleId);
}
