package life.pahtlicoo.application.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GetRequestFilterReqDTO {
    @NotNull
    private int userId;
    private Integer state;
    private Integer day;
    private Integer month;
    private Integer year;
}
