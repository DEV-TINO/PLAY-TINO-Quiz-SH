package Spring.SpringTino.Bean;

import Spring.SpringTino.domain.Quiz;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ConvertListToString{
    public String exec(List<Quiz> QuizList) {

        //ObjectMapper를 이요해
        ObjectMapper objectMapper = new ObjectMapper();
        String stringQuizList = "";
        try {
            stringQuizList = objectMapper.writeValueAsString(QuizList);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }

        return stringQuizList;
    }
}
