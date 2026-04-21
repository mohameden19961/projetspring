package mr.supnum.library_app.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class AuthorResponseDTO {
    private Long id;
    private String name;
    private String bio;
    private String nationalityCode;
    private String nationalityName;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
