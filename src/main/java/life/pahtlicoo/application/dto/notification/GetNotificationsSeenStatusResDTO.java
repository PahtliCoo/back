/**
 * Response DTO to retrieve seen status of notification
 * @author Luis Enrique Salazar Perez
 * @since 2025-06-08
 */
package life.pahtlicoo.application.dto.notification;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class GetNotificationsSeenStatusResDTO {
    private boolean status;
}
