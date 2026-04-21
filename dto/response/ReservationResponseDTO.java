package mr.supnum.library_app.dto.response;

import lombok.Getter;
import lombok.Setter;
import mr.supnum.library_app.entity.enums.ReservationStatus;

import java.time.LocalDateTime;

@Getter
@Setter
public class ReservationResponseDTO {
    private Long id;
    private Long memberId;
    private String memberName;
    private Long bookId;
    private String bookTitle;
    private Integer queuePosition;
    private LocalDateTime reservedAt;
    private LocalDateTime expiresAt;
    private ReservationStatus status;
    private Long fulfilledByBorrowId;
    private String notes;
}
