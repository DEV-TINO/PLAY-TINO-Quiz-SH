package Spring.SpringTino.repository;

import Spring.SpringTino.domain.Comment;
import Spring.SpringTino.domain.QuizRank;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface JpaCommentRepository extends JpaRepository<Comment, UUID> {
     Comment findByCommentIdAndUserId(UUID commentId, UUID userId);

    Page<Comment> findAllByOrderByUploadTimeDesc(Pageable pageable);
    Page<Comment> findAllByOrderByHeartCountDesc(Pageable pageable);
}
