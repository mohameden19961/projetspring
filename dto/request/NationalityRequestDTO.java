package mr.supnum.library_app.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NationalityRequestDTO {
    @NotBlank
    @Size(min = 2, max = 3)
    private String code;

    @NotBlank
    @Size(max = 100)
    private String name;
}
