package Spring.SpringTino.Bean;

import Spring.SpringTino.domain.Comment;
import Spring.SpringTino.repository.JpaCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class GetCommentDAOBean {
    JpaCommentRepository jpaCommentRepository;

    @Autowired
    public GetCommentDAOBean(JpaCommentRepository jpaCommentRepository) {
        this.jpaCommentRepository = jpaCommentRepository;
    }

    public Comment exec(UUID commentId, UUID userId) {
        return jpaCommentRepository.findByCommentIdAndUserId(commentId, userId);
    }

    public Comment exec(UUID commentId) {
        return jpaCommentRepository.findById(commentId).get();
    }
}
