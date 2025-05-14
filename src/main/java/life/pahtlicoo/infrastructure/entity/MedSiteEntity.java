package life.pahtlicoo.infrastructure.entity;


import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="med_site")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MedSiteEntity extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@ManyToMany
    @JoinColumn(name="site_id")
    private int siteId;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    //@ManyToOne
    @JoinColumn(name=("med_id"))
    private int medId;
}
