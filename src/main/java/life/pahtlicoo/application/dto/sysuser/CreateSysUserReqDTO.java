/**
 * Create System User ReqDto.
 * @author Santiago Moreno Lacalle Quintero (A01663197@tec.mx)
 * @since 2025-05-26
 */
package life.pahtlicoo.application.dto.sysuser;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class CreateSysUserReqDTO {
    private String email;
    private String password;
    private String name;
    private String lastName;
    private int siteId;
    private int credentialId;
}
