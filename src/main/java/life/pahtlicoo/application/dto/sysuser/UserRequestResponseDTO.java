package life.pahtlicoo.application.dto.sysuser;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserRequestResponseDTO {
    @NotBlank
    private int userId;
    @NotBlank
    private int rolId;
    @NotBlank
    private String name;
}
