package mr.supnum.library_app.dto.response;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class PublisherResponseDTO {
    private Long id;
    private String name;
    private String address;
    private String email;
    private String website;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
