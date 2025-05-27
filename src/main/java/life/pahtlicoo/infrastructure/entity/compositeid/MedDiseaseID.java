/**
 * Auxiliary class for composite primary key in med disease.
 * @author Adolfo Hernandez Fernandez (a01664412@tec.mx)
 * @co-author Nicole Kapellmann Lepine (a01664563@tec.mx)
 * @since 2025-05-26
 */
package life.pahtlicoo.infrastructure.entity.compositeid;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class MedDiseaseID {
    @Column(name = "med_id")
    private int medId;

    @Column(name = "disease_id")
    private int diseaseId;
}
