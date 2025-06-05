package life.pahtlicoo.application.dto.sysuser;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateEmailRequestDTO {
    private int sysUserId;
    private String newEmail;
    private String firebaseId;
}
