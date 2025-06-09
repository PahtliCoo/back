/**
 * Site Entity.
 * @author Santiago Moreno Lacalle Quintero (A01663197@tec.mx)
 * @co-author Adolfo Hernandez Fernandez (a01664412@tec.mx)
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
@Table(name="site")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SiteEntity extends PanacheEntityBase {
    @Id
    @Column(name="site_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int siteId;

    @Column(name="name")
    private String name;

    @Column(name="region")
    private String region;

    @Column(name = "created_at")
    @CreationTimestamp
    private OffsetDateTime createdAt;

    @Column(name="updated_at")
    @UpdateTimestamp
    private OffsetDateTime updatedAt;
}
