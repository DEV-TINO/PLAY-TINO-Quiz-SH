package Spring.SpringTino.Bean;

import Spring.SpringTino.domain.QuizRank;
import Spring.SpringTino.repository.JpaQuizRankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SaveQuizRankDAOBean {
    JpaQuizRankRepository jpaQuizRankRepository;

    @Autowired
    public SaveQuizRankDAOBean(JpaQuizRankRepository jpaQuizRankRepository) {
        this.jpaQuizRankRepository = jpaQuizRankRepository;
    }

    public QuizRank exec(QuizRank quizRank) {
        return jpaQuizRankRepository.save(quizRank);
    }
}
