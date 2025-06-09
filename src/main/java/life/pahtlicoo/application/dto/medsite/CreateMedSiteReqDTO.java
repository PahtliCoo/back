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
public class CreateMedSiteReqDTO {
    private int site_id;
    private int med_id;
    private int current_quantity;
    private int initial_quantity;
}
