/**
 * User class.
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
public class User {
    private int userId;
    private String name;
    private String lastName;
    private String email;
    private int siteId;
    private int roleId; // Esto debería de ser rol.
    private String firebaseId;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
