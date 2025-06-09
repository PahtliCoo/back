/**
 * Historic Data class.
 * @author Adolfo Hernández Fernández (a01664412@tec.mx)
 * @co-author Nicole Kapellmann Lepine (a01664563@tec.mx)
 * @since 2025-05-11
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
public class HistoricData {
    private int historicDataId;
    private int siteId;
    private int dateYear;
    private int dateMonth;
    private int medId;
    private int quantity;
    private int projectedQuantity;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
