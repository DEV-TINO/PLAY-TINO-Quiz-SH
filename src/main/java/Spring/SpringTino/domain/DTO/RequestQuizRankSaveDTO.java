package Spring.SpringTino.domain.DTO;

import lombok.Data;

import java.util.UUID;

@Data
public class RequestQuizRankSaveDTO {
    UUID gameId;
    UUID userId;
    String userName;
    Integer nonsenseCorrect;
    Integer commonsenseCorrect;
    Integer allCorrect;
}
