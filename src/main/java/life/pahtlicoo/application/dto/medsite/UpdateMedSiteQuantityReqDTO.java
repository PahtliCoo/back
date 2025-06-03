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
public class UpdateMedSiteQuantityReqDTO {
    @NotBlank
    private int medId;
    @NotBlank
    private int siteId;
    @NotBlank
    private int quantity;
}
