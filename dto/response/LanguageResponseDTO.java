package mr.supnum.library_app.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class LanguageResponseDTO {
    private String code;
    private String name;
    private LocalDateTime createdAt;
}
