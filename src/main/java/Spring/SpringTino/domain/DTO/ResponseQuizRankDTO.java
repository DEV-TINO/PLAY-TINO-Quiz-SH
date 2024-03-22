package Spring.SpringTino.domain.DTO;

import lombok.Data;

import java.util.UUID;

@Data
public class ResponseQuizRankDTO {
    UUID quizRankId;
    String userName;
    Integer nonsenseCorrect;
    Integer commonsenseCorrect;
    Integer allCorrect;
}
