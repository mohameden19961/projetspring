package mr.supnum.library_app.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class BorrowRequestDTO {
    @NotNull
    private Long memberId;

    @NotNull
    private Long bookItemId;

    private Long reservationId;

    private BigDecimal finePerDay;
}
