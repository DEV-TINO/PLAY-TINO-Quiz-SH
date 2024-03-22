package Spring.SpringTino.Bean;

import Spring.SpringTino.domain.Quiz;
import Spring.SpringTino.repository.JpaQuizGameRepository;
import Spring.SpringTino.repository.JpaQuizRankRepository;
import Spring.SpringTino.repository.JpaQuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Objects;

@Component
public class GetQuizCommonsenseDAOBean {
    JpaQuizRankRepository jpaQuizRankRepository;
    JpaQuizGameRepository jpaQuizGameRepository;
    JpaQuizRepository jpaQuizRepository;

    @Autowired
    public GetQuizCommonsenseDAOBean(JpaQuizGameRepository jpaQuizGameRepository, JpaQuizRankRepository jpaQuizRankRepository, JpaQuizRepository jpaQuizRepository) {
        this.jpaQuizRankRepository = jpaQuizRankRepository;
        this.jpaQuizRepository = jpaQuizRepository;
        this.jpaQuizGameRepository = jpaQuizGameRepository;
    }

    //category : "상식" 을 가진 Quiz 객체를 찾고 반환
    public int exec(List<Quiz> quizList) {
        int count = 0;

        //quizList에 있는 10개의 문제중 정답이고 카테고리가 상식이면 정답 개수 1개 증가
        for(Quiz quiz : quizList) {
            if(Objects.equals(quiz.getCorrect(), quiz.getAnswer()) && Objects.equals(quiz.getCategory(), "상식")) {
                count += 1;
            }
        }

        //맞춘 상식 정답 개수를 반환
        return count;
    }
}
