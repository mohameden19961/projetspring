package mr.supnum.library_app.dto.response;

import lombok.Getter;
import lombok.Setter;
import mr.supnum.library_app.entity.enums.BorrowStatus;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class BorrowResponseDTO {
    private Long id;
    private Long memberId;
    private String memberName;
    private Long bookItemId;
    private String barcode;
    private String bookTitle;
    private Long reservationId;
    private LocalDateTime borrowDate;
    private LocalDateTime returnDate;
    private LocalDateTime returnedAt;
    private BorrowStatus status;
    private Integer renewalCount;
    private BigDecimal finePerDay;
    private boolean isArchived;
}
