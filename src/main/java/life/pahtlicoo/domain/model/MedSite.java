/**
 * Med Site class.
 * @author Adolfo Hernández Fernández (a01664412@tec.mx)
 * @since 2025-05-11
 */
package life.pahtlicoo.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MedSite {
    private int medId;
    private int siteId;
    private int initialQuantity; //added
    private int currentQuantity; //added
    private OffsetDateTime createdAt; //added
    private OffsetDateTime updatedAt;//added
}