/**
 * Request Detail entity.
 * @author Nicole Kapellmann Lepine (a01664563@tec.mx)
 * @co-author Adolfo Hernandez Fernandez (a01664412@tec.mx)
 * @since 2025-05-26
 */

package life.pahtlicoo.infrastructure.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import life.pahtlicoo.infrastructure.entity.compositeid.RequestDetailID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.OffsetDateTime;

@Entity
@Table(name = "request_detail")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestDetailEntity extends PanacheEntityBase {
    
    @EmbeddedId
    private RequestDetailID requestDetailID;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "created_at")
    @CreationTimestamp
    private OffsetDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private OffsetDateTime updatedAt;
}
