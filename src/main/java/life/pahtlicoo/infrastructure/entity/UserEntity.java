/**
 * JPA User entity.
 * @author Adolfo Hernández Fernández (a01664412@tec.mx)
 * @since 2025-05-21
 */
package life.pahtlicoo.infrastructure.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.OffsetDateTime;

@Entity
@Table(name="user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class UserEntity extends PanacheEntityBase {
    @Id
    @Column(name="user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name="last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name="firebase_id")
    private String firebaseId;

    @Column(name = "created_at")
    @CreationTimestamp
    private OffsetDateTime createdAt;

    @Column(name="updated_at")
    @UpdateTimestamp
    private OffsetDateTime updatedAt;

    @Column(name="role_id")
    private int roleId;

    @Column(name="site_id")
    private int siteId;
}
