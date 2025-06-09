/**
 * Request Detail DTO.
 * @author Santiago Moreno Lacalle Quintero (A01663197@tec.mx)
 * @since 2025-05-28
 */
package life.pahtlicoo.application.dto.requestdetail;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateRequestDetailReqDTO {
    @NotBlank
    private int requestId;
    @NotEmpty
    private List<MedicineQuantityReqDTO> requestDetailsList;
}
