package mr.supnum.library_app.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import mr.supnum.library_app.entity.enums.MemberStatus;
import mr.supnum.library_app.entity.enums.MemberType;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;

@Entity
@Table(name = "members")
@SQLDelete(sql = "UPDATE members SET is_deleted=true, deleted_at=NOW() WHERE id=?")
@Where(clause = "is_deleted = false")
@Getter
@Setter
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    private String phone;

    @Enumerated(EnumType.STRING)
    @Column(name = "member_type", nullable = false)
    private MemberType memberType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private MemberStatus status;

    @Column(name = "max_borrows")
    private Integer maxBorrows;

    @Column(name = "member_since")
    private LocalDateTime memberSince;
}
