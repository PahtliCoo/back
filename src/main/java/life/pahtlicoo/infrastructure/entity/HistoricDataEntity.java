/**
 * JPA Historic Data entity.
 * @author Adolfo Hernández Fernández (a01664412@tec.mx)
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
@Table(name="historic_data")
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class HistoricDataEntity extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="historic_data_id")
    private int historicDataId;

    @Column(name="site_id")
    private int siteId;

    @Column(name="year")
    private int year;

    @Column(name="month")
    private int month;

    @Column(name="med_id")
    private int medId;

    @Column(name="quantity")
    private int quantity;

    @Column(name = "created_at")
    @CreationTimestamp
    private OffsetDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    private OffsetDateTime updatedAt;
}
