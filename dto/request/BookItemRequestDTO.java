package mr.supnum.library_app.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import mr.supnum.library_app.entity.enums.BookStatus;

import java.math.BigDecimal;
import java.time.LocalDate;

@Getter
@Setter
public class BookItemRequestDTO {
    @NotNull
    private Long bookId;

    @NotBlank
    @Size(max = 100)
    private String barcode;

    @NotNull
    private BookStatus status;

    private LocalDate acquiredAt;

    private BigDecimal price;

    private String notes;
}
