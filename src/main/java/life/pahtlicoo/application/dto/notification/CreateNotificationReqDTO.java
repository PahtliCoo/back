package life.pahtlicoo.application.dto.notification;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CreateNotificationReqDTO {
    private int seen;
    private String description;
    @NotBlank
    private int sender_id;
    @NotBlank
    private int receiver_id;
    @NotBlank
    private int request_id;
}
