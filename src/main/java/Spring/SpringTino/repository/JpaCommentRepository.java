package Spring.SpringTino.repository;

import Spring.SpringTino.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface JpaCommentRepository extends JpaRepository<Comment, UUID> {
}
