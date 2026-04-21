package mr.supnum.library_app.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PublisherRequestDTO {
    @NotBlank
    @Size(max = 150)
    private String name;

    private String address;

    @Email
    private String email;

    private String website;
}
