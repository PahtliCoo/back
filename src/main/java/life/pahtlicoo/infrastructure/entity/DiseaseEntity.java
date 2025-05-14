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
@Table (name = "disease")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DiseaseEntity extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="disease_id")
    public int diseaseId;

    @Column(name = "name")
    public String name;

    @Column(name = "created_at")
    @CreationTimestamp
    public OffsetDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    public OffsetDateTime updatedAt;
}
