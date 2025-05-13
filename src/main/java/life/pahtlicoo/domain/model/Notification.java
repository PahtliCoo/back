/**
 * Notification class.
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
public class Notification {
    private int notificationId;
    private String status;
    private String description;
    private int senderId;
    private int receiverId;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
    private int requestId;
}
