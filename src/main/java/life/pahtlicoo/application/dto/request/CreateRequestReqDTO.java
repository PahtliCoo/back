/**
 * Request Detail DTO.
 * @Author Adolfo Hernandez Fernandez
 * @co-author Santiago Moreno Lacalle Quintero (A01663197@tec.mx)
 * @since 2025-05-28
 */
package life.pahtlicoo.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import life.pahtlicoo.application.dto.requestdetail.MedicineQuantityReqDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateRequestReqDTO {
    @NotBlank
    private int sys_user_id;
    @NotBlank
    private int state;

    private String description;

    @NotEmpty
    private List<MedicineQuantityReqDTO> requestDetailList;
}
