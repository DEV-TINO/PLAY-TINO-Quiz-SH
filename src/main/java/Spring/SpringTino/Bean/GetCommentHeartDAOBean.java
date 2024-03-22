package Spring.SpringTino.Bean;

import Spring.SpringTino.domain.CommentHeart;
import Spring.SpringTino.repository.JpaCommentHeartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class GetCommentHeartDAOBean {
    JpaCommentHeartRepository jpaCommentHeartRepository;

    @Autowired
    public GetCommentHeartDAOBean(JpaCommentHeartRepository jpaCommentHeartRepository) {
        this.jpaCommentHeartRepository = jpaCommentHeartRepository;
    }

    public CommentHeart exec(UUID commentId, UUID userId) {
        return jpaCommentHeartRepository.findByCommentIdAndUserId(commentId, userId);
    }
}
