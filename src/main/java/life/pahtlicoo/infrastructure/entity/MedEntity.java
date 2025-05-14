package life.pahtlicoo.infrastructure.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

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
    private OffsetDateTime created_at;
    @Column(name="updated_at")
    private OffsetDateTime updated_at;

}
