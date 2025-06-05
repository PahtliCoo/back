package life.pahtlicoo.infrastructure.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import io.quarkus.panache.common.Sort;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import life.pahtlicoo.domain.model.Request;
import life.pahtlicoo.domain.model.ShipmentOrder;
import life.pahtlicoo.domain.repository.ShipmentOrderRepository;
import life.pahtlicoo.infrastructure.entity.RequestEntity;
import life.pahtlicoo.infrastructure.entity.ShipmentOrderEntity;
import life.pahtlicoo.infrastructure.mapper.ShipmentOrderEntityMapper;

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

    /*

    @Override
    public List<ShipmentOrder> getAllShipmentOrdersByUserIdByStateAndDate(int sysUserId, int state, int year,int month, int day,int page){
        List<ShipmentOrderEntity> shipmentOrderEntities = find(
                "sysUserId = ?1 and state = ?2 and YEAR(createdAt) = ?3 and MONTH(createdAt) = ?4 and " +
                        "DAY(createdAt) = ?5",
                Sort.descending("createdAt"),
                sysUserId, state, year, month, day
        ).page(page,5).list();
        if(shipmentOrderEntities == null){
            return null;
        }
        return shipmentOrderEntities.stream()
                .map(shipmentOrderEntityMapper::toDomain)
                .toList();
    }

    @Override
    public List<ShipmentOrder> getAllShipmentOrdersByUserIdByState(int sysUserId, int state,int page){
        List<ShipmentOrderEntity> shipmentOrderEntities = find("sysUserId = ?1 and state = ?2",Sort.descending("createdAt"),sysUserId,state).page(page,5).list();
        if(shipmentOrderEntities == null){
            return null;
        }
        return shipmentOrderEntities.stream()
                .map(shipmentOrderEntityMapper::toDomain)
                .toList();
    }

    @Override
    public List<ShipmentOrder> getAllShipmentOrdersByUserIdByDate(int sysUserId, int year, int month, int day,int page){
        List<ShipmentOrderEntity> shipmentOrderEntities = find(
                "sysUserId = ?1 and YEAR(createdAt) = ?2 and MONTH(createdAt) = ?3 and " +
                        "DAY(createdAt) = ?4",
                Sort.descending("createdAt"),
                sysUserId, year, month, day
        ).page(page,5).list();
        if(shipmentOrderEntities == null){
            return null;
        }
        return shipmentOrderEntities.stream()
                .map(shipmentOrderEntityMapper::toDomain)
                .toList();
    }
    */

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

}
