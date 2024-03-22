package Spring.SpringTino.repository;

import Spring.SpringTino.domain.QuizGame;
import Spring.SpringTino.domain.QuizRank;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface JpaQuizRankRepository extends JpaRepository<QuizRank, UUID> {
    QuizRank findByGameIdAndUserId(UUID gameId, UUID userId);
    Page<QuizRank> findAllByOrderByAllCorrectDesc(Pageable pageable);
}
