package life.pahtlicoo.application.dto.med;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MedUpdateNameReqDTO {
    @NotBlank
    private int medId;
    @NotBlank
    private String name;
}
