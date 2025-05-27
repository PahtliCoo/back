/**
 * Request class.
 * @author Adolfo Hernández Fernández (a01664412@tec.mx)
 * @since 2025-05-11
 */
package life.pahtlicoo.application.mapper;

import jakarta.enterprise.context.ApplicationScoped;
import life.pahtlicoo.application.dto.request.CreateRequestReqDTO;
import life.pahtlicoo.domain.model.Request;

@ApplicationScoped
public class RequestDomainMapper {
    public Request createRequestToDomain(CreateRequestReqDTO createRequestReqDTO){
        Request request = new Request();
        request.setSysUserId(createRequestReqDTO.getSys_user_id());
        request.setState(createRequestReqDTO.getState());
        request.setDescription(createRequestReqDTO.getDescription());
        return request;
    }
}
