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
@Table(name = "notification")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NotificationEntity extends PanacheEntityBase {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="notification_id")
    public int notificationId;

    @Column(name="status")
    public String status;

    @Column(name="description")
    public String description;

    @Column(name="sender_id")
    public int senderId;

    @Column(name="receiver_id")
    public int receiverId;

    @Column(name="request_id")
    public int requestId;

    @Column(name = "created_at")
    @CreationTimestamp
    public OffsetDateTime createdAt;

    @Column(name = "updated_at")
    @UpdateTimestamp
    public OffsetDateTime updatedAt;
}
