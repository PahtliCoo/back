/**
 * Response containing data of inventory
 * @author Adolfo Hernández Fernández (a01664412@tec.mx)
 * @since 2025-06-05
 */
package life.pahtlicoo.application.dto.medsite;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MedSiteResDTO {
    @NotBlank
    private String med_name;
    @NotBlank
    private String site_name;
    private int total_consumed;
    private int initial_quantity;
    private int current_quantity;
    private int med_id;
    private int site_id;
}
