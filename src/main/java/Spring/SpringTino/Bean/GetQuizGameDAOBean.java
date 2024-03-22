package Spring.SpringTino.Bean;

import Spring.SpringTino.domain.QuizGame;
import Spring.SpringTino.repository.JpaQuizGameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class GetQuizGameDAOBean {
    JpaQuizGameRepository jpaQuizGameRepository;

    @Autowired
    public GetQuizGameDAOBean(JpaQuizGameRepository jpaQuizGameRepository) {
        this.jpaQuizGameRepository = jpaQuizGameRepository;
    }

    public QuizGame exec(UUID gameId, UUID userId) {
        return jpaQuizGameRepository.findByGameIdAndUserId(gameId, userId);
    }
}
