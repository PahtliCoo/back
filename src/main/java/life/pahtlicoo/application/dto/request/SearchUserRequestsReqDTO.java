/**
 * Filter to obtain user requests with optional filters.
 * @author Adolfo Hernández Fernández (a01664412@tec.mx)
 * @since 2025-06-05
 */
package life.pahtlicoo.application.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class SearchUserRequestsReqDTO {
    private int sysUserId;
    private String name;
    private String date;  // YYYY-MM-DD
    private Integer state;
    private int page;
}
