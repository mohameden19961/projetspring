package mr.supnum.library_app.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReservationRequestDTO {
    @NotNull
    private Long memberId;

    @NotNull
    private Long bookId;

    private String notes;
}
