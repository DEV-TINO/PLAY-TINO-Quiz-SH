package Spring.SpringTino.domain.DTO;

import Spring.SpringTino.domain.Quiz;
import lombok.Data;

import java.util.List;
import java.util.UUID;

@Data
public class ResponseQuizDTO {
    UUID quizId;
    String question;
    String correct;
    String answer;
    String hint;
    String category;
}
