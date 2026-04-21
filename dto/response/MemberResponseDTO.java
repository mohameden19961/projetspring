package mr.supnum.library_app.dto.response;

import lombok.Getter;
import lombok.Setter;
import mr.supnum.library_app.entity.enums.MemberStatus;
import mr.supnum.library_app.entity.enums.MemberType;

import java.time.LocalDateTime;

@Getter
@Setter
public class MemberResponseDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private MemberType memberType;
    private MemberStatus status;
    private Integer maxBorrows;
    private LocalDateTime memberSince;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
