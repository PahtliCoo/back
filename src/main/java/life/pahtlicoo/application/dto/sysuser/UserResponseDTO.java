package life.pahtlicoo.application.dto.sysuser;


import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponseDTO {
    private int sysUserId;
    private String name;
    private String lastName;
    private String email;
    private String firebaseId;
}