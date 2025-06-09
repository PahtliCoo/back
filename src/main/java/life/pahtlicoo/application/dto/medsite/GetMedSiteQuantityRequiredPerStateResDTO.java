package life.pahtlicoo.application.dto.medsite;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetMedSiteQuantityRequiredPerStateResDTO {
    private String siteRegion;
    private int quantity;
}
