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
public class SearchUserRequestsByNameReqDTO {
    private int sys_user_id;
    @NotBlank
    private String name;
    private int page;
}
