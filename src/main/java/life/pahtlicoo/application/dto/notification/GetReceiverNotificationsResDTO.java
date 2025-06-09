/**
 * Receiver notifications information response DTO
 * @author Luis Enrique Salazar Perez
 * @since 2025-06-08
 */
package life.pahtlicoo.application.dto.notification;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetReceiverNotificationsResDTO {
    private int notificationId;
    private boolean seen;
    private String description;
    private OffsetDateTime updatedAt;
}
