/**
 * MedicineQuantity dTO.
 * @author Santiago Moreno Lacalle Quintero (A01663197@tec.mx)
 * @since 2025-05-28
 */
package life.pahtlicoo.application.dto.requestdetail;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MedicineQuantityReqDTO {
    @NotNull
    private int medId;
    @NotNull
    @Min(1)
    private int quantity;
}
