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
    @NotBlank
    private int siteId;
    @NotBlank
    private int medId;
}
