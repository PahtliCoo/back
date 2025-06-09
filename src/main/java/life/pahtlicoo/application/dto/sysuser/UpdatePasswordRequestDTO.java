package life.pahtlicoo.application.dto.sysuser;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePasswordRequestDTO {
    private int sys_user_id;
    @NotBlank
    private String new_password;
}
