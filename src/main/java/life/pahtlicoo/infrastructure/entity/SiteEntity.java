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
import java.util.List;

@Entity
@Table(name="site")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SiteEntity extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int site_id;

    @Column(name="name")
    private String name;

    @Column(name="region")
    private String region;

    @Column(name = "created_at")
    @CreationTimestamp
    private OffsetDateTime created_at;

    @Column(name="updated_at")
    @UpdateTimestamp
    private OffsetDateTime updated_at;

    @ManyToMany(mappedBy = "sites", fetch = FetchType.LAZY)
    private List<MedEntity> meds;
}
