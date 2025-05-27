/**
 * Credential Mapper DTO application.
 * @author Adolfo Hernández Fernández (a01664412@tec.mx)
 * @co-author Santiago Moreno Lacalle Quintero (A01663197@tec.mx)
 * @since 2025-05-26
 */
package life.pahtlicoo.application.mapper;

import jakarta.enterprise.context.ApplicationScoped;
import life.pahtlicoo.application.dto.credential.CreateCredentialReqDTO;
import life.pahtlicoo.domain.model.Credential;


@ApplicationScoped
public class CredentialDomainMapper {
    public Credential createRoleToDomain(CreateCredentialReqDTO createRoleReqDTO){
        Credential credential = new Credential();
        credential.setName(createRoleReqDTO.getName());
        return credential;
    }
}
