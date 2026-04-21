package mr.supnum.library_app.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookRequestDTO {
    @NotBlank
    @Size(max = 255)
    private String title;

    @NotBlank
    @Size(max = 20)
    private String isbn;

    private Integer publicationYear;

    private String languageCode;

    private Integer editionNumber;

    private String summary;

    @NotNull
    private Long categoryId;

    @NotNull
    private Long publisherId;
}
