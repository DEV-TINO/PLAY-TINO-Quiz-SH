package Spring.SpringTino.repository;

import Spring.SpringTino.domain.CommentHeart;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaCommentHeartRepository extends JpaRepository<CommentHeart, UUID> {
    CommentHeart findByCommentIdAndUserId(UUID commentId, UUID userId);
    Boolean existsByCommentIdAndUserId(UUID commentId, UUID UserId);
}
