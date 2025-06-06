/**
 * Med Response
 * @Author Santiago Moreno Lacalle Quintero (A01663197@tec.mx)
 * @co-author Adolfo Hernandez Fernandez
 * @since 2025-06-05
 */
package life.pahtlicoo.application.dto.med;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import jakarta.validation.constraints.NotBlank;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MedResponseDTO {
    private int med_id;
    @NotBlank
    private String med_name;
}
