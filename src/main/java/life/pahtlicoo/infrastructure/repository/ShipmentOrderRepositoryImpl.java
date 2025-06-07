package life.pahtlicoo.infrastructure.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import life.pahtlicoo.application.dto.request.SearchUserRequestsReqDTO;
import life.pahtlicoo.application.dto.shipmentorder.SearchShipmentOrdersReqDTO;
import life.pahtlicoo.domain.model.Request;
import life.pahtlicoo.domain.model.ShipmentOrder;
import life.pahtlicoo.domain.repository.ShipmentOrderRepository;
import life.pahtlicoo.infrastructure.entity.RequestEntity;
import life.pahtlicoo.infrastructure.entity.ShipmentOrderEntity;
import life.pahtlicoo.infrastructure.mapper.ShipmentOrderEntityMapper;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

@ApplicationScoped
public class ShipmentOrderRepositoryImpl implements ShipmentOrderRepository, PanacheRepositoryBase<ShipmentOrderEntity, Integer> {
    @Inject
    ShipmentOrderEntityMapper shipmentOrderEntityMapper;

    @Override
    @Transactional
    public void createShipmentOrder(ShipmentOrder shipmentOrder) {
        ShipmentOrderEntity shipmentOrderEntity = shipmentOrderEntityMapper.toEntity(shipmentOrder);
        persist(shipmentOrderEntity);
        shipmentOrder.setShipmentOrderId(shipmentOrderEntity.getShipmentOrderId());
    }

    @Override
    public ShipmentOrder getShipmentOrder(int shipmentOrderId) {
        ShipmentOrderEntity shipmentOrderEntity = findById(shipmentOrderId);
        if (shipmentOrderEntity == null) {
            return null;
        }
        return shipmentOrderEntityMapper.toDomain(shipmentOrderEntity);
    }

    @Override
    @Transactional
    public void updateShipmentOrderStatus(int shipmentOrderId, int state) {
        ShipmentOrderEntity shipmentOrderEntity = findById(shipmentOrderId);
        if (shipmentOrderEntity == null) {
            return;
        }
        shipmentOrderEntity.setState(state);
    }

    @Override
    @Transactional
    public void deleteShipmentOrder(int shipmentOrderId) {
        deleteById(shipmentOrderId);
    }

    @Override
    public List<ShipmentOrder> getAllShipmentOrder(int page){
        List<ShipmentOrderEntity> shipmentOrderEntities = findAll(Sort.descending("createdAt")).page(page, 5).list();
        return shipmentOrderEntities.stream()
                .map(shipmentOrderEntityMapper::toDomain)
                .toList();
    }

    @Override
    public List<ShipmentOrder> getAllShipmentOrdersByDate(int year, int month, int day,int page){
        List<ShipmentOrderEntity> shipmentOrderEntities = find("YEAR(createdAt) = ?1 and MONTH(createdAt) = ?2 and " +
                "DAY(createdAt) = ?3", Sort.descending("createdAt"),year,month,day).page(page,5).list();
        if(shipmentOrderEntities == null){
            return null;
        }
        return shipmentOrderEntities.stream()
                .map(shipmentOrderEntityMapper::toDomain)
                .toList();
    }
    @Override
    public List<ShipmentOrder> getAllShipmentOrdersByDateByState(int state,int year, int month, int day,int page){
        List<ShipmentOrderEntity> shipmentOrderEntities = find(" state = ?1 and YEAR(createdAt) = ?2 and " +
                "MONTH(createdAt) = ?3 and " + "DAY(createdAt) = ?4",Sort.descending("createdAt"),state,year,month,day).page(page,5).list();
        if(shipmentOrderEntities == null){
            return null;
        }
        return shipmentOrderEntities.stream()
                .map(shipmentOrderEntityMapper::toDomain)
                .toList();
    }
    @Override
    public List<ShipmentOrder> getAllShipmentOrdersByState(int state, int page){
        List<ShipmentOrderEntity> shipmentOrderEntities = find("state",Sort.descending("createdAt"),state).page(page,5).list();
        if(shipmentOrderEntities == null) {
            return null;
        }
        return shipmentOrderEntities.stream()
                .map(shipmentOrderEntityMapper::toDomain)
                .toList();
    }

    @Override
    public List<ShipmentOrder> getAllShipmentOrdersBySearch(String search, int page){
        search = search.toLowerCase();
        List<ShipmentOrderEntity> shipmentOrderEntities = find(
                "CAST(requestId AS string) LIKE ?1 OR LOWER(name) LIKE ?1",
                "%" + search + "%"
        ).page(page, 5).list();

        if(shipmentOrderEntities == null){
            return null;
        }
        return shipmentOrderEntities.stream()
                .map(shipmentOrderEntityMapper::toDomain)
                .toList();
    }

    @Override
    public List<ShipmentOrder> searchShipmentOrders(SearchShipmentOrdersReqDTO searchShipmentOrdersReqDTO){
        StringBuilder query = new StringBuilder("1=1");
        List<Object> params = new ArrayList<>();
        int paramIndex = 1;

        // 1. Buscar dentro de REQUESTENTITY
        if (searchShipmentOrdersReqDTO.getName() != null && !searchShipmentOrdersReqDTO.getName().isBlank()) {
            // Si existe nos regresa la entidad de request
            List<RequestEntity> requests = RequestEntity.find("LOWER(name) LIKE ?1",
                    "%" + searchShipmentOrdersReqDTO.getName().toLowerCase() + "%").list();

            if (requests.isEmpty()) {
                return new ArrayList<>();
            }

            // Utilizamos sus id para la busqueda
            StringBuilder inClause = new StringBuilder();
            for (int i = 0; i < requests.size(); i++) {
                if (i > 0) inClause.append(",");
                inClause.append(requests.get(i).getRequestId());
            }
            query.append(" AND requestId IN (" + inClause + ")");
        }


        if (searchShipmentOrdersReqDTO.getState() != null) {
            query.append(" AND state = ?" + paramIndex);
            params.add(searchShipmentOrdersReqDTO.getState());
            paramIndex++;
        }

        if (searchShipmentOrdersReqDTO.getDate() != null && !searchShipmentOrdersReqDTO.getDate().isBlank()) {
            LocalDate date = LocalDate.parse(searchShipmentOrdersReqDTO.getDate());
            OffsetDateTime start = date.atStartOfDay().atOffset(ZoneOffset.UTC);
            OffsetDateTime end = date.plusDays(1).atStartOfDay().atOffset(ZoneOffset.UTC);

            query.append(" AND createdAt >= ?" + paramIndex);
            params.add(start);
            paramIndex++;

            query.append(" AND createdAt < ?" + paramIndex);
            params.add(end);
            paramIndex++; //Solo se va a usar si añadimos un if nuevo
        }

        // Ejecutar la consulta construida
        List<ShipmentOrderEntity> shipmentOrderEntities = find(query.toString(),
                Sort.descending("createdAt"),
                params.toArray())
                .page(searchShipmentOrdersReqDTO.getPage(), 5)
                .list();



        return shipmentOrderEntities.stream()
                .map(shipmentOrderEntityMapper::toDomain)
                .toList();
    }

}
