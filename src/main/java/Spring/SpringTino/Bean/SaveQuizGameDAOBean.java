package Spring.SpringTino.Bean;

import Spring.SpringTino.domain.Quiz;
import Spring.SpringTino.domain.QuizGame;
import Spring.SpringTino.repository.JpaQuizGameRepository;
import Spring.SpringTino.repository.JpaQuizRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SaveQuizGameDAOBean {
    JpaQuizGameRepository jpaQuizGameRepository;

    @Autowired
    public SaveQuizGameDAOBean(JpaQuizGameRepository jpaQuizGameRepository) {
        this.jpaQuizGameRepository = jpaQuizGameRepository;
    }

    public QuizGame exec(QuizGame quizGame) {
        return jpaQuizGameRepository.save(quizGame);
    }
}
