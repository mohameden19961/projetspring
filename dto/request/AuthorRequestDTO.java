package mr.supnum.library_app.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthorRequestDTO {
    @NotBlank
    @Size(max = 150)
    private String name;

    private String bio;

    private String nationalityCode;
}
