package Spring.SpringTino.Bean;

import Spring.SpringTino.domain.QuizRank;
import Spring.SpringTino.repository.JpaQuizRankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class GetQuizRankDAOBean {
    JpaQuizRankRepository jpaQuizRankRepository;

    @Autowired
    public GetQuizRankDAOBean(JpaQuizRankRepository jpaQuizRankRepository) {
        this.jpaQuizRankRepository = jpaQuizRankRepository;
    }

    public QuizRank exec(UUID gameId, UUID userId) {
        return jpaQuizRankRepository.findByGameIdAndUserId(gameId, userId);
    }
}
