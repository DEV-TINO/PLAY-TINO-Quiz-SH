package Spring.SpringTino.Bean;

import Spring.SpringTino.domain.Quiz;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ConvertStringToList {

    public List<Quiz> exec(String stringQuizList) {

        ObjectMapper objectMapper = new ObjectMapper();
        List<Quiz> quizList;
        try {
            quizList = objectMapper.readValue(stringQuizList, new TypeReference<List<Quiz>>() {});
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to deserialize quiz list.", e);
        }
        return quizList;
    }
}
