package mr.supnum.library_app.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

@Entity
@Table(name = "authors")
@SQLDelete(sql = "UPDATE authors SET is_deleted=true, deleted_at=NOW() WHERE id=?")
@Where(clause = "is_deleted = false")
@Getter
@Setter
public class Author extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 150)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String bio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nationality_code")
    private Nationality nationality;
}
