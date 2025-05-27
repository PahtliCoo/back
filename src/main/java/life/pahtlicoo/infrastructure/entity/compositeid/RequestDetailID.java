/**
 * Auxiliary class for composite primary key in request detail.
 * @author Nicole Kapellmann Lepine (a01664563@tec.mx)
 * @co-author Adolfo Hernandez Fernandez (a01664412@tec.mx)
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
public class RequestDetailID {
    @Column(name = "med_id")
    private int medId;

    @Column(name = "request_id")
    private int requestId;
}
