/**
 * Shipment Order entity.
 * @author Adolfo Hernandez Fernandez (a01664412@tec.mx)
 * @co-author Nicole Kapellmann Lepine (a01664563@tec.mx)
 * @since 2025-05-26
 */
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
    private int shipmentOrderId;

    @Column(name="request_id")
    private int requestId;

    @Column(name="description")
    private String description;

    @Column(name= "state")
    private int state;

    @Column(name="created_at")
    @CreationTimestamp
    private OffsetDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private OffsetDateTime updatedAt;

}
