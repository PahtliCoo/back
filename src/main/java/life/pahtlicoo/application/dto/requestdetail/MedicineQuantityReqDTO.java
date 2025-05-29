/**
 * MedicineQuantity dTO.
 * @author Santiago Moreno Lacalle Quintero (A01663197@tec.mx)
 * @since 2025-05-28
 */
package life.pahtlicoo.application.dto.requestdetail;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MedicineQuantityReqDTO {
    private int medId;
    private int quantity;
}
