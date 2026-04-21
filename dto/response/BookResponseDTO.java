package mr.supnum.library_app.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class BookResponseDTO {
    private Long id;
    private String title;
    private String isbn;
    private Integer publicationYear;
    private String languageCode;
    private String languageName;
    private Integer editionNumber;
    private String summary;
    private Long categoryId;
    private String categoryName;
    private Long publisherId;
    private String publisherName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
