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
public class UpdateEmailUnifiedRequestDTO {
    private int sysUserId;

    @NotBlank
    private String firebaseId;

    @NotBlank
    private String newEmail;
}
