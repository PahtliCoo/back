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
public class UpdateMedDiseaseByMedIdReqDTO {
    @NotBlank
    private int oldMedId;
    @NotBlank
    private int newMedId;
}
