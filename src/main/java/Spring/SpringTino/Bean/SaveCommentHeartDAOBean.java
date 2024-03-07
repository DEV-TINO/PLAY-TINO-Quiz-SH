package Spring.SpringTino.Bean;

import Spring.SpringTino.domain.CommentHeart;
import Spring.SpringTino.repository.JpaCommentHeartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class SaveCommentHeartDAOBean {

    JpaCommentHeartRepository jpaCommentHeartRepository;

    @Autowired
    public SaveCommentHeartDAOBean(JpaCommentHeartRepository jpaCommentHeartRepository) {
        this.jpaCommentHeartRepository = jpaCommentHeartRepository;
    }

    //댓글 좋아요 저장
    public void exec(CommentHeart commentHeart) {
        jpaCommentHeartRepository.save(commentHeart);
    }
}
