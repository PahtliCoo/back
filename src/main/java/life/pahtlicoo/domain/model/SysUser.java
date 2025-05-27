/**
 * User class.
 * @author Adolfo Hernández Fernández (a01664412@tec.mx)
 * @co-author Nicole Kapellmann Lepine (a01664563@tec.mx)
 * @since 2025-05-26
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
public class SysUser {
    private int sysUserId;
    private String name;
    private String lastName;
    private String email;
    private int siteId;
    private int credentialId;
    private String firebaseId;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
}
