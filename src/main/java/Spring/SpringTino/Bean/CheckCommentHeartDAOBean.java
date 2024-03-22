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

    //commentID와 userId를 통해 유저가 comment 에 좋아요를 눌렀는지 boolean 값으로 반환
    public boolean checkMyHeart(UUID commentId, UUID userId) {
        return jpaCommentHeartRepository.existsByCommentIdAndUserId(commentId, userId);
    }
}
