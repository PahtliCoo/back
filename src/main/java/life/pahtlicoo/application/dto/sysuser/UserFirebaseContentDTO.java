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
public class UserFirebaseContentDTO {
    private String uid;
}
