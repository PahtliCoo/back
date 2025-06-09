/**
 * Request Repository.
 * @author Adolfo Hernández Fernández (a01664412@tec.mx)
 * @Co-Author Santiago Moreno Lacalle Quintero (A01663197@tec.mx)
 * @since 2025-06-05
 */
package life.pahtlicoo.infrastructure.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import life.pahtlicoo.application.dto.request.SearchUserRequestsReqDTO;
import life.pahtlicoo.domain.model.Request;
import life.pahtlicoo.domain.repository.RequestRepository;
import life.pahtlicoo.infrastructure.entity.RequestEntity;
import life.pahtlicoo.infrastructure.entity.SysUserEntity;
import life.pahtlicoo.infrastructure.mapper.RequestEntityMapper;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class RequestRepositoryImpl implements RequestRepository, PanacheRepositoryBase<RequestEntity, Integer> {

    @Inject
    RequestEntityMapper requestEntityMapper;

    @Override
    @Transactional
    public boolean createRequest(Request request){
        RequestEntity requestEntity = requestEntityMapper.toEntity(request);
        persist(requestEntity);
        // Generated Correctly
        if(requestEntity.isPersistent()){
            request.setRequestId(requestEntity.getRequestId());
            return true;
        }
        // Generated Incorrectly
        return false;
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
        List<RequestEntity> requestEntities = find("sysUserId",Sort.descending("createdAt") ,sysUserId).list();

        if(requestEntities == null){
            return null;
        }

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
    public boolean deleteRequest(int requestId){
        return deleteById(requestId);
    }

    @Override
    public List<Request> getAllRequestsByUserIdByStateAndDate(int sysUserId, int state, int year,int month, int day,int page){
        List<RequestEntity> requestEntities = find(
                "sysUserId = ?1 and state = ?2 and YEAR(createdAt) = ?3 and MONTH(createdAt) = ?4 and " +
                        "DAY(createdAt) = ?5",
                Sort.descending("createdAt"),
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
        List<RequestEntity> requestEntities = find("sysUserId = ?1 and state = ?2",Sort.descending("createdAt"),sysUserId,state).page(page,5).list();
        if(requestEntities == null){
            return null;
        }
        return requestEntities.stream()
                .map(requestEntityMapper::toDomain)
                .toList();
    }

    @Override
    public List<Request> getAllRequestsByUserIdByDate(int sysUserId, int year, int month, int day,int page){
        List<RequestEntity> requestEntities = find(
                "sysUserId = ?1 and YEAR(createdAt) = ?2 and MONTH(createdAt) = ?3 and " +
                        "DAY(createdAt) = ?4",
                Sort.descending("createdAt"),
                sysUserId, year, month, day
        ).page(page,5).list();
        if(requestEntities == null){
            return null;
        }
        return requestEntities.stream()
                .map(requestEntityMapper::toDomain)
                .toList();
    }
    @Override
    public List<Request> getAllRequest(int page){
        List<RequestEntity> requestEntities = RequestEntity.findAll(Sort.descending("createdAt")).page(page,5).list();
        if(requestEntities == null){
            return null;
        }
        return requestEntities.stream()
                .map(requestEntityMapper::toDomain)
                .toList();
    }
    @Override
    public List<Request> getAllRequestsByDate(int year, int month, int day,int page){
        List<RequestEntity> requestEntities = find("YEAR(createdAt) = ?1 and MONTH(createdAt) = ?2 and " +
                "DAY(createdAt) = ?3", Sort.descending("createdAt"),year,month,day).page(page,5).list();
        if(requestEntities == null){
            return null;
        }
        return requestEntities.stream()
                .map(requestEntityMapper::toDomain)
                .toList();
    }
    @Override
    public List<Request> getAllRequestsByDateByState(int state,int year, int month, int day,int page){
        List<RequestEntity> requestEntities = find(" state = ?1 and YEAR(createdAt) = ?2 and " +
                "MONTH(createdAt) = ?3 and " + "DAY(createdAt) = ?4",Sort.descending("createdAt"),state,year,month,day).page(page,5).list();
        if(requestEntities == null){
            return null;
        }
        return requestEntities.stream()
                .map(requestEntityMapper::toDomain)
                .toList();
    }
    @Override
    public List<Request> getAllRequestsByState(int state, int page){
        List<RequestEntity> requestEntities = find("state",Sort.descending("createdAt"),state).page(page,5).list();
        if(requestEntities == null) {
            return null;
        }
        return requestEntities.stream()
                .map(requestEntityMapper::toDomain)
                .toList();
    }

    @Override
    public List<Request> getAllRequestsBySearch(String search, int page){
        search = search.toLowerCase();
        List<RequestEntity> requestEntities = find(
                "CAST(requestId AS string) LIKE ?1 OR LOWER(name) LIKE ?1",
                "%" + search + "%"
        ).page(page, 5).list();

        if(requestEntities == null){
            return null;
        }
        return requestEntities.stream()
                .map(requestEntityMapper::toDomain)
                .toList();
    }

    @Override
    public List<Request> searchUserRequestsByName(int sysUserId, String name, int page){
        name = name.toLowerCase();
        List<RequestEntity> requestEntities = find("sysUserId = ?1 AND LOWER(name) LIKE ?2",
                Sort.descending("createdAt") ,sysUserId,"%"+name+"%").page(page, 5).list();

        return requestEntities.stream()
                .map(requestEntityMapper::toDomain)
                .toList();
    }

    @Override
    public List<Request> searchUserRequests(SearchUserRequestsReqDTO searchUserRequestsReqDTO) {
        SysUserEntity sysUser = SysUserEntity.findById(searchUserRequestsReqDTO.getSysUserId());

        if (sysUser == null) {
            throw new IllegalArgumentException("SysUser not found");
        }

        boolean isLogisticsAdmin = sysUser.getCredentialId() == 2;

        StringBuilder query = new StringBuilder("""
        SELECT r FROM RequestEntity r
        """);

        List<Object> params = new ArrayList<>();
        int paramIndex = 1;

        // WHERE clause
        query.append("WHERE ");

        if (!isLogisticsAdmin) {
            query.append("r.sysUserId = ?").append(paramIndex++);
            params.add(sysUser.getSysUserId());
        } else {
            query.append("1=1");
        }

        // Optional name filter
        if (searchUserRequestsReqDTO.getName() != null && !searchUserRequestsReqDTO.getName().isBlank()) {
            query.append(" AND LOWER(r.name) LIKE ?").append(paramIndex);
            params.add("%" + searchUserRequestsReqDTO.getName().toLowerCase() + "%");
            paramIndex++;
        }

        // Optional state filter
        if (searchUserRequestsReqDTO.getState() != null) {
            query.append(" AND r.state = ?").append(paramIndex);
            params.add(searchUserRequestsReqDTO.getState());
            paramIndex++;
        }

        // Optional date filter
        if (searchUserRequestsReqDTO.getDate() != null && !searchUserRequestsReqDTO.getDate().isBlank()) {
            LocalDate date = LocalDate.parse(searchUserRequestsReqDTO.getDate());
            OffsetDateTime start = date.atStartOfDay().atOffset(ZoneOffset.UTC);
            OffsetDateTime end = date.plusDays(1).atStartOfDay().atOffset(ZoneOffset.UTC);

            query.append(" AND r.createdAt >= ?").append(paramIndex);
            params.add(start);
            paramIndex++;

            query.append(" AND r.createdAt < ?").append(paramIndex);
            params.add(end);
            paramIndex++;
        }

        List<RequestEntity> requestEntities = find(
                query.toString(),
                Sort.descending("r.updatedAt"),
                params.toArray()
        ).page(searchUserRequestsReqDTO.getPage(), 10).list();

        return requestEntities.stream()
                .map(requestEntityMapper::toDomain)
                .toList();
    }


    @Override
    @Transactional
    public void updateRequestDescription(int requestId, String description){
        RequestEntity requestEntity = findById(requestId);
        if(requestEntity != null){
            requestEntity.setDescription(description);
        }
    }

}
