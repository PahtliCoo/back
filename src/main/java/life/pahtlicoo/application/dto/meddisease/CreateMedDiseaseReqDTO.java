package life.pahtlicoo.application.dto.meddisease;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateMedDiseaseReqDTO {
    @NotBlank
    private int medId;
    @NotBlank
    private int diseaseId;
}
