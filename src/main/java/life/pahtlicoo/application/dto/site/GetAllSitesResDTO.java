package life.pahtlicoo.application.dto.site;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetAllSitesResDTO {
    private int site_id;
    private String name;
    private String region;
}
