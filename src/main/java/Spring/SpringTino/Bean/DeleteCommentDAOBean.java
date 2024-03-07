package Spring.SpringTino.Bean;

import Spring.SpringTino.domain.Comment;
import Spring.SpringTino.repository.JpaCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeleteCommentDAOBean {
    JpaCommentRepository jpaCommentRepository;

    @Autowired
    public DeleteCommentDAOBean(JpaCommentRepository jpaCommentRepository) {
        this.jpaCommentRepository = jpaCommentRepository;
    }

    //댓글 삭제
    public void exec(Comment comment) {
        jpaCommentRepository.delete(comment);
    }
}
