package life.pahtlicoo.infrastructure.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import life.pahtlicoo.domain.model.Role;
import life.pahtlicoo.domain.repository.RoleRepository;
import life.pahtlicoo.infrastructure.entity.RoleEntity;
import life.pahtlicoo.infrastructure.mapper.RoleEntityMapper;

import java.util.List;

@ApplicationScoped
public class RoleRepositoryImpl implements RoleRepository, PanacheRepositoryBase<RoleEntity, Integer> {
    @Inject
    RoleEntityMapper roleEntityMapper;

    @Override
    @Transactional
    public void createRole(Role role){
        RoleEntity roleEntity = roleEntityMapper.toEntity(role);
        persist(roleEntity);
        role.setRoleId(roleEntity.getRoleId());
    }

    @Override
    public Role getRole(int roleId){
        RoleEntity roleEntity = findById(roleId);
        if(roleEntity == null){
            return null;
        }
        return roleEntityMapper.toDomain(roleEntity);
    }

    @Override
    public List<Role> getAllRoles(){
        List<RoleEntity> roleEntities = RoleEntity.listAll();
        return roleEntities.stream()
                .map(roleEntityMapper::toDomain)
                .toList();
    }

    @Override
    @Transactional
    public void updateRoleName(int roleId, String newRoleName){
        RoleEntity roleEntity = findById(roleId);
        if(roleEntity == null){
            return;
        }
        roleEntity.setName(newRoleName);
    }

    @Override
    @Transactional
    public void deleteRole(int roleId){
        deleteById(roleId);
    }
}

