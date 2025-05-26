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
    //private String password; //SI? 
    private String name;
    private String lastName;
    private int siteId;
    private int credentialId;
}
