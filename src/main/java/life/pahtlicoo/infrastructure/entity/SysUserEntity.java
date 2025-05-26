package life.pahtlicoo.infrastructure.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.security.Timestamp;
import java.time.OffsetDateTime;

@Entity
@Table(name="sys_user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class SysUserEntity extends PanacheEntityBase {
    @Id
    @Column(name="sys_user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name="last_name")
    private String lastName;

    @Column(name = "email")
    private String email;

    @Column(name = "site_id")
    private int siteId;

    @Column(name = "credential_id")
    private int credentialId;

    @Column(name="firebase_id")
    private String firebaseId;

    @Column(name = "created_at")
    @CreationTimestamp
    private OffsetDateTime createdAt;

    @Column(name="updated_at")
    @UpdateTimestamp
    private OffsetDateTime updatedAt;
}
