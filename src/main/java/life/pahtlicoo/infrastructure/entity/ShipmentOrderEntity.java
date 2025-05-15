package life.pahtlicoo.infrastructure.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.OffsetDateTime;

@Entity
@Table (name = "shipment_order")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ShipmentOrderEntity extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="shipment_order_id")
    public int shipmentOrderId;

    @Column(name="user_id")
    public int userId;

    @Column(name="request_id")
    public int requestId;

    @Column(name="description")
    public String description;

    @Column(name= "status")
    public String status;

    @Column(name="created_at")
    @CreationTimestamp
    public OffsetDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    public OffsetDateTime updatedAt;

}
