package life.pahtlicoo.application.dto.sysuser;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateEmailUnifiedRequestDTO {
    private int sysUserId;
    private String firebaseId;
    private String newEmail;
}
