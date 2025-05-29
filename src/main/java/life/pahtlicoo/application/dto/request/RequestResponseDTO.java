package life.pahtlicoo.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import life.pahtlicoo.domain.model.Request;
import life.pahtlicoo.domain.model.RequestDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestResponseDTO {
    @NotBlank
    Request request;
    @NotEmpty
    List<RequestDetail> requestDetail;
}
