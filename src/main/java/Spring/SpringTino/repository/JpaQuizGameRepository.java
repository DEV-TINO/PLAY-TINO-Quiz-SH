package Spring.SpringTino.repository;

import Spring.SpringTino.domain.QuizGame;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface JpaQuizGameRepository extends JpaRepository<QuizGame, UUID> {
    QuizGame findByGameIdAndUserId(UUID gameId, UUID userId);
//    Boolean existsByGameIdAndUserId(UUID gameId, UUID userId);
}
