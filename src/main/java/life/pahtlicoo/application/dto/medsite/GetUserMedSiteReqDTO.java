/**
 * Filter to obtain user inventory with optional filters.
 * @author Adolfo Hernández Fernández (a01664412@tec.mx)
 * @since 2025-06-05
 */
package life.pahtlicoo.application.dto.medsite;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetUserMedSiteReqDTO {
    private int sysUserId;
    private String medName;
    private int page;
}
