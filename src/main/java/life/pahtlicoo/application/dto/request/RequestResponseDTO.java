/**
 * Request response DTO.
 * @Author Santiago Moreno Lacalle Quintero (A01663197@tec.mx)
 * @since 2025-05-30
 */
package life.pahtlicoo.application.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.annotation.concurrent.NotThreadSafe;
import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RequestResponseDTO {
    @NotBlank
    private int requestId;
    @NotBlank
    private String siteName;
    @NotBlank
    private String requestName;
    private String description;
    @NotBlank
    private int state;
    private OffsetDateTime createdAt;
}
