package life.pahtlicoo.infrastructure.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
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
@Table(name="med")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class MedEntity extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int med_id;

    @Column(name="name")
    private String name;

    @Column(name = "created_at")
    @CreationTimestamp
    private OffsetDateTime created_at;

    @Column(name="updated_at")
    @UpdateTimestamp
    private OffsetDateTime updated_at;

    @ManyToMany(mappedBy = "site",cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
            name="med_site",
            joinColumns = @JoinColumn(name="med_id"),
            inverseJoinColumns = @JoinColumn(name="site_id")
    )
    private List<SiteEntity> sites;
}
