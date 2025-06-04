/**
 * Request Repository.
 * @author Adolfo Hernández Fernández (a01664412@tec.mx)
 * @Co-Author Santiago Moreno Lacalle Quintero (A01663197@tec.mx)
 * @since 2025-05-11
 */
package life.pahtlicoo.infrastructure.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import life.pahtlicoo.domain.model.Request;
import life.pahtlicoo.domain.repository.RequestRepository;
import life.pahtlicoo.infrastructure.entity.RequestEntity;
import life.pahtlicoo.infrastructure.mapper.RequestEntityMapper;

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
    //TODO if possible make a single method that based on what it receives changes the query

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
        //TODO, dependiendo del tiempo impleemntar busqueda por estado
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
        name = name.toLowerCase(); //TODO porque no necesito implementar otra variable o poner String al inicio?
        System.out.println("Esto es lo que va a buscar: "+ name);
        System.out.println("Esto es lo que va a buscar de sys user id: "+ sysUserId);
        System.out.println("Esto es lo que va a buscar de page: "+ page);

        List<RequestEntity> requestitasEntities = find("sysUserId", Sort.descending("createdAt") ,sysUserId).list(); //TODO hacer lo de paginación pero que devuelva todo aunque no llegue al minimo de elementos de una pagina
        System.out.println("Esto es lo que encuentra solo por user Id: "+ requestitasEntities);

        List<RequestEntity> requestEntities = find("sysUserId = ?1 AND LOWER(name) LIKE ?2", Sort.descending("createdAt") ,sysUserId,"%"+name+"%").list();
        //TODO vale la pena tener paginacion?

        System.out.println("Estas son las request entities: "+ requestEntities);

//        if(requestEntities == null){
//            return null;
//        }

        return requestEntities.stream()
                .map(requestEntityMapper::toDomain)
                .toList();

    }

}
