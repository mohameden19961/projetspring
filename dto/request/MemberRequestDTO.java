package mr.supnum.library_app.dto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;
import mr.supnum.library_app.entity.enums.MemberStatus;
import mr.supnum.library_app.entity.enums.MemberType;

@Getter
@Setter
public class MemberRequestDTO {
    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    @Email
    private String email;

    private String phone;

    @NotNull
    private MemberType memberType;

    @NotNull
    private MemberStatus status;

    private Integer maxBorrows;
}
