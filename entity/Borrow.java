package mr.supnum.library_app.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import mr.supnum.library_app.entity.enums.BorrowStatus;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "borrows")
@EntityListeners(AuditingEntityListener.class)
@Getter
@Setter
public class Borrow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false)
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_item_id", nullable = false)
    private BookItem bookItem;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "reservation_id")
    private Reservation reservation;

    @CreatedDate
    @Column(name = "borrow_date", nullable = false, updatable = false)
    private LocalDateTime borrowDate;

    @Column(name = "return_date", nullable = false)
    private LocalDateTime returnDate;

    @Column(name = "returned_at")
    private LocalDateTime returnedAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BorrowStatus status;

    @Column(name = "renewal_count")
    private Integer renewalCount = 0;

    @Column(name = "fine_per_day", precision = 8, scale = 2)
    private BigDecimal finePerDay;

    @Column(name = "is_archived", nullable = false)
    private boolean isArchived = false;
}
