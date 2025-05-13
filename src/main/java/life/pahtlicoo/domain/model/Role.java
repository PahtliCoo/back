/**
 * Role class.
 * @author Adolfo Hernández Fernández (a01664412@tec.mx)
 * @since 2025-05-11
 */
package life.pahtlicoo.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Role {
    private int roleId;
    private String name;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
