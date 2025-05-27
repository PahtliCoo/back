/**
 * Med Site entity.
 * @author Nicole Kapellmann Lepine (a01664563@tec.mx)
 * @co-author Adolfo Hernandez Fernandez (a01664412@tec.mx)
 * @since 2025-05-26
 */
package life.pahtlicoo.infrastructure.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import life.pahtlicoo.infrastructure.entity.compositeid.MedSiteID;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.OffsetDateTime;

@Entity
@Table(name="med_site")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MedSiteEntity {
    @EmbeddedId
    private MedSiteID medSiteID;

    @Column(name="initial_quantity")
    private int initialQuantity;

    @Column(name="current_quantity")
    private int currentQuantity;

    @CreationTimestamp
    @Column(name = "created_at")
    private OffsetDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at")
    private OffsetDateTime updatedAt;

}
