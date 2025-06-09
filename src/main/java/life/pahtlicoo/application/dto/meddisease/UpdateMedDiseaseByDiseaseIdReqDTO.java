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
public class UpdateMedDiseaseByDiseaseIdReqDTO {
    @NotBlank
    private int oldDiseaseId;
    @NotBlank
    private int newDiseaseId;
}
