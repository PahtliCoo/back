package life.pahtlicoo.application.dto.sysuser;

import jakarta.enterprise.context.RequestScoped;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@RequestScoped
//TODO: Quiero pregutanrle a andres si esta bien que sea un requestedScopte
// https://marcelkliemannel.com/articles/2021/overview-of-bean-scopes-in-quarkus/#:%7E:text=If%20a%20bean%20class%20has%20the%20annotation%20%40RequestScoped%20%2C%20CDI%20will,process%20a%20single%20HTTP%20request.
public class UserFirebaseContentDTO {
    private String uid;
}
