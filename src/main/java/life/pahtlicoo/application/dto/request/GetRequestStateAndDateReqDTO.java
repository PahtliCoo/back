package life.pahtlicoo.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetRequestStateAndDateReqDTO {
    @NotBlank
    private int state;
    @NotBlank
    private int year;
    @NotBlank
    private int month;
    @NotBlank
    private int day;
}
