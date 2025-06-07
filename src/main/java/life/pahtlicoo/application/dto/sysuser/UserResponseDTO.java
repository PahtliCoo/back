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
public class UserResponseDTO {
    private int sysUserId;
    @NotBlank
    private String name;
    @NotBlank
    private String lastName;

    private String email;
    @NotBlank
    private String firebaseId;
}