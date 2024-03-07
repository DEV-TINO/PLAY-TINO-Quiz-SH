package Spring.SpringTino.Bean;

import Spring.SpringTino.domain.Comment;
import Spring.SpringTino.domain.CommentHeart;
import Spring.SpringTino.domain.DTO.ResponseCommentDTO;
import Spring.SpringTino.repository.JpaCommentHeartRepository;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CheckCommentHeartDAOBean {
    JpaCommentHeartRepository jpaCommentHeartRepository;

    @Autowired
    public CheckCommentHeartDAOBean(JpaCommentHeartRepository jpaCommentHeartRepository) {
        this.jpaCommentHeartRepository = jpaCommentHeartRepository;
    }

    public boolean checkMyHeart(UUID commentId, UUID userId) {
        return jpaCommentHeartRepository.existsByCommentIdAndUserId(commentId, userId);
    }
}
