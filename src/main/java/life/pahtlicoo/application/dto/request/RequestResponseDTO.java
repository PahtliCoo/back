package life.pahtlicoo.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestResponseDTO {
    @NotBlank
    private int requestId;
    //TODO Change


    @NotBlank
    private String description;
    @NotBlank
    private int state;

}
