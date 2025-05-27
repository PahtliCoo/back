/**
 * Credentials Req DTO
 * @author Adolfo Hernández Fernández (a01664412@tec.mx)
 * @since 2025-05-26
 */
package life.pahtlicoo.application.dto.credential;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateCredentialReqDTO {
    @NotBlank
    private String name;
}
