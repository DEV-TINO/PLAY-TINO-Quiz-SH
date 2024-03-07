package Spring.SpringTino.Bean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import Spring.SpringTino.domain.Comment;
import Spring.SpringTino.repository.JpaCommentRepository;

@Component
public class SaveCommentDAOBean {

    JpaCommentRepository jpaCommentRepository;

    @Autowired
    public SaveCommentDAOBean(JpaCommentRepository jpaCommentRepository){
        this.jpaCommentRepository = jpaCommentRepository;
    }

    // 댓글 저장
    public void exec(Comment comment){
        jpaCommentRepository.save(comment);
    }
}