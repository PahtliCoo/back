/**
 * Get all request detail Use case.
 * @author Adolfo Hernández Fernández (a01664412@tec.mx)
 * @since 2025-06-05
 */

package life.pahtlicoo.application.dto.requestdetail;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetRequestDetailResDTO {
    private int med_quantity;
    @NotBlank
    private String med_name;
}
