package mr.supnum.library_app.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Table(name = "nationalities")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Nationality {

    @Id
    @Column(length = 3)
    private String code;

    @Column(nullable = false, length = 100)
    private String name;

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;
}
