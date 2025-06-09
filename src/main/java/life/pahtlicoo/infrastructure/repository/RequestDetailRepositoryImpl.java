/**
 * Request Detail Repository Impl.
 * @author Santiago Moreno Lacalle Quintero (A01663197@tec.mx)
 * @since 2025-05-28
 */
package life.pahtlicoo.infrastructure.repository;


import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import life.pahtlicoo.domain.model.RequestDetail;
import life.pahtlicoo.domain.repository.RequestDetailRepository;
import life.pahtlicoo.infrastructure.entity.RequestDetailEntity;
import life.pahtlicoo.infrastructure.mapper.RequestDetailEntityMapper;
import java.util.List;

@ApplicationScoped
public class RequestDetailRepositoryImpl implements RequestDetailRepository, PanacheRepositoryBase<RequestDetailEntity,Integer> {
    @Inject
    RequestDetailEntityMapper requestDetailEntityMapper;

    @Override
    @Transactional
    public boolean createRequestDetail(List<RequestDetail> requestDetailList) {
        // 1. Get all the quantity and med_id
        for (RequestDetail requestDetail : requestDetailList) {
            RequestDetailEntity requestDetailEntity = requestDetailEntityMapper.toEntity(requestDetail);
            persist(requestDetailEntity);
            // Didn't create correctly
            if(!requestDetailEntity.isPersistent()){
                return false;
            }
        }
        return true;
    }

    @Override
    public List<RequestDetail> getRequestDetailsByRequestId(int requestId){
        // 1. Get all values that have the same requestId
        List<RequestDetailEntity> requestDetailEntity= RequestDetailEntity.list("requestDetailID.requestId", requestId);
        if(requestDetailEntity == null){
            return null;
        }
        return requestDetailEntity.stream().map(requestDetailEntityMapper::toDomain).toList();
    }

    @Override
    @Transactional
    public boolean deleteRequestDetail(int requestId) {
        // 1. Delete all cases where requestId is the same
        return RequestDetailEntity.delete("requestDetailID.requestId", requestId) > 0;
    }
}
