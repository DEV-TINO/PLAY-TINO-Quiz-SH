package Spring.SpringTino.domain.DTO;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
public class RequestCommentSaveDTO {
    UUID userId;
    String content;
}
