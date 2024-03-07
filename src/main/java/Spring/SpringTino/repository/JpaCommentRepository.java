package Spring.SpringTino.repository;

import Spring.SpringTino.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface JpaCommentRepository extends JpaRepository<Comment, UUID> {
//    List<Comment> findAllOrderByUploadTimeDesc(LocalDateTime uploadTime);
}
