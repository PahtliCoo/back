package life.pahtlicoo.infrastructure.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.security.Timestamp;
import java.time.OffsetDateTime;

@Entity
@Table(name="user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class UserEntity extends PanacheEntity {
    @Id
    @GeneratedValue
    public int id;

    @Column(name = "name")
    public String name;

    @Column(name="lastName")
    public String lastName;

    @Column(name = "email")
    public String email;

    @Column(name="firebase_id")
    public String firebase_id;

    @Column(name = "created_at")
    public OffsetDateTime created_at;

    @Column(name="updated_at")
    public OffsetDateTime updated_at;
    //Todo: rol y site
}
