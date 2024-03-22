package Spring.SpringTino.Bean;

import Spring.SpringTino.domain.Quiz;
import Spring.SpringTino.repository.JpaQuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class GetQuizNonsenseDAOBean {
    JpaQuizRepository jpaQuizRepository;

    @Autowired
    public GetQuizNonsenseDAOBean(JpaQuizRepository jpaQuizRepository) {
        this.jpaQuizRepository = jpaQuizRepository;
    }

    //category : "넌센스" 를 가진 Quiz 객체를 찾고 반환
    public int exec(List<Quiz> quizList) {
        int count = 0;

        //10개의 문제에서 쓴 답과 정답이 같고 카테고리가 넌센스이면 count 1증가
        for(Quiz quiz : quizList) {
            if(Objects.equals(quiz.getCorrect(), quiz.getAnswer()) && Objects.equals(quiz.getCategory(), "넌센스")) {
                count += 1;
            }
        }
        return count;
    }
}
