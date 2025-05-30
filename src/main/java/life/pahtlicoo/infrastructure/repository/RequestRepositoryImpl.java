/**
 * Request Repository.
 * @author Adolfo Hernández Fernández (a01664412@tec.mx)
 * @Co-Author Santiago Moreno Lacalle Quintero (A01663197@tec.mx)
 * @since 2025-05-11
 */
package life.pahtlicoo.infrastructure.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import life.pahtlicoo.domain.model.Request;
import life.pahtlicoo.domain.repository.RequestRepository;
import life.pahtlicoo.infrastructure.entity.RequestEntity;
import life.pahtlicoo.infrastructure.mapper.RequestEntityMapper;

import java.util.Date;
import java.util.List;

@ApplicationScoped
public class RequestRepositoryImpl implements RequestRepository, PanacheRepositoryBase<RequestEntity, Integer> {

    @Inject
    RequestEntityMapper requestEntityMapper;

    @Override
    @Transactional
    public void createRequest(Request request){
        RequestEntity requestEntity = requestEntityMapper.toEntity(request);
        persist(requestEntity);
        request.setRequestId(requestEntity.getRequestId());
    }

    @Override
    public Request getRequest(int requestId){
        RequestEntity requestEntity = findById(requestId);
        if(requestEntity == null){
            return null;
        }
        return requestEntityMapper.toDomain(requestEntity);
    }

    @Override
    public List<Request> getAllRequestsByUserId(int sysUserId){
        List<RequestEntity> requestEntities = find("sysUserId", sysUserId).list();
        return requestEntities.stream()
                .map(requestEntityMapper::toDomain)
                .toList();
    }

    @Override
    @Transactional
    public void updateRequestStatus(int sysUserId, int state){
        RequestEntity requestEntity = findById(sysUserId);
        if(requestEntity == null){
            return;
        }
        requestEntity.setState(state);
    }

    @Override
    @Transactional
    public void deleteRequest(int requestId){
        deleteById(requestId);
    }

    @Override
    public List<Request> getAllRequestsByUserIdByStateAndDate(int sysUserId, int state, int year,int month, int day,int page){
        List<RequestEntity> requestEntities = find(
                "sysUserId = ?1 and state = ?2 and YEAR(createdAt) = ?3 and MONTH(createdAt) = ?4 and " +
                        "DAY(createdAt) = ?5",
                sysUserId, state, year, month, day
        ).page(page,5).list();
        if(requestEntities == null){
            return null;
        }
        return requestEntities.stream()
                .map(requestEntityMapper::toDomain)
                .toList();
    }

    @Override
    public List<Request> getAllRequestsByUserIdByState(int sysUserId, int state,int page){
        List<RequestEntity> requestEntities = find("sysUserId = ?1 and state = ?2",sysUserId,state).page(page,5).list();
        if(requestEntities == null){
            return null;
        }
        return requestEntities.stream()
                .map(requestEntityMapper::toDomain)
                .toList();
    }

}
