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
public class GetNotificationReqDTO {
    private int notificationId;
    private boolean seen;
    private String description;
    private OffsetDateTime updatedAt;
}
