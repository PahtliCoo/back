/**
 * JPA Historic Data entity.
 * @author Adolfo Hernández Fernández (a01664412@tec.mx)
 * @since 2025-05-21
 */

package life.pahtlicoo.infrastructure.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
public class MedSiteEntity extends PanacheEntityBase {
    @Id//Actually a foreign key
    @Column(name="med_id")
    private int medId;

    //Actually a foreign key
    @Column(name="site_id")
    private int siteId;

    @Column(name="quantity")
    private int quantity;

    @Column(name = "created_at")
    @CreationTimestamp
    private OffsetDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private OffsetDateTime updatedAt;
}
