package mr.supnum.library_app.dto.response;

import lombok.Getter;
import lombok.Setter;
import mr.supnum.library_app.entity.enums.BookStatus;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class BookItemResponseDTO {
    private Long id;
    private Long bookId;
    private String bookTitle;
    private String barcode;
    private BookStatus status;
    private LocalDate acquiredAt;
    private BigDecimal price;
    private String notes;
    private Integer version;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
