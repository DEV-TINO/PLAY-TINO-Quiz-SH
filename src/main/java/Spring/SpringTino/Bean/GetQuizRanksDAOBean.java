package Spring.SpringTino.Bean;

import Spring.SpringTino.domain.QuizRank;
import Spring.SpringTino.repository.JpaQuizRankRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GetQuizRanksDAOBean {
    JpaQuizRankRepository jpaQuizRankRepository;

    @Autowired
    public GetQuizRanksDAOBean(JpaQuizRankRepository jpaQuizRankRepository) {
        this.jpaQuizRankRepository = jpaQuizRankRepository;
    }

    public Page<QuizRank> exec(Pageable pageable) {
        return jpaQuizRankRepository.findAllByOrderByAllCorrectDesc(pageable);
    }
}
