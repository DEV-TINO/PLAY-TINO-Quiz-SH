package Spring.SpringTino.domain.DTO;

import lombok.Data;

import java.util.UUID;

@Data
public class RequestCommentHeartDeleteDTO {
    UUID commentId;
    UUID userId;
}
