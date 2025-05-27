/**
 * Med Site class.
 * @author Adolfo Hernández Fernández (a01664412@tec.mx)
 * @co-author Nicole Kapellmann Lepine (a01664563@tec.mx)
 * @since 2025-05-26
 */
package life.pahtlicoo.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MedSite {
    private int medId;
    private int siteId;
    private int initialQuantity;
    private int currentQuantity;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}