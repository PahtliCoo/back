/**
 * Request Detail DTO.
 * @Author Adolfo Hernandez Fernandez
 * @co-author Santiago Moreno Lacalle Quintero (A01663197@tec.mx)
 * @since 2025-05-28
 */
package life.pahtlicoo.application.dto.request;

import jakarta.validation.constraints.*;
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
    private int sys_user_id;
    private int state;
    @NotBlank
    private String name;
    private String description;
    @NotEmpty
    private List<MedicineQuantityReqDTO> request_detail_list;
}
