package life.pahtlicoo.application.dto.medsite;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterMedSiteAdditionDTO {
    private int medId;
    private int siteId;
    private int addition;
}
