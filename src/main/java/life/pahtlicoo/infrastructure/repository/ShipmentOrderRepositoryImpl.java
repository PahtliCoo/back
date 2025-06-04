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
    public List<ShipmentOrder> getAllShipmentOrder(){
        List<ShipmentOrderEntity> shipmentOrderEntities = findAll(Sort.descending("createdAt")).list();
        return shipmentOrderEntities.stream()
                .map(shipmentOrderEntityMapper::toDomain)
                .toList();
    }
}
