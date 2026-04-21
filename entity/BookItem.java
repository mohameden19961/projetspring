package mr.supnum.library_app.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import mr.supnum.library_app.entity.enums.BookStatus;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "book_items")
@SQLDelete(sql = "UPDATE book_items SET is_deleted=true, deleted_at=NOW() WHERE id=?")
@Where(clause = "is_deleted = false")
@Getter
@Setter
public class BookItem extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", nullable = false)
    private Book book;

    @Column(nullable = false, unique = true, length = 100)
    private String barcode;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BookStatus status;

    @Column(name = "acquired_at")
    private LocalDate acquiredAt;

    @Column(precision = 10, scale = 2)
    private BigDecimal price;

    @Column(columnDefinition = "TEXT")
    private String notes;

    @Version
    private Integer version;
}
