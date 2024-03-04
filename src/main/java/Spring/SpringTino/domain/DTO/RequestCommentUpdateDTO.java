package Spring.SpringTino.domain.DTO;

import lombok.Data;

import java.util.UUID;

@Data
public class RequestCommentUpdateDTO {
    UUID commentId;
    UUID userId;
    String content;
}
