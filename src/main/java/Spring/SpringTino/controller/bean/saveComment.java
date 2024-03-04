package Spring.SpringTino.controller.bean;

import Spring.SpringTino.repository.JpaCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class saveComment {

    JpaCommentRepository jpaCommentRepository;

    @Autowired
    public saveComment(JpaCommentRepository jpaCommentRepository) {
        this.jpaCommentRepository = jpaCommentRepository;
    }


}
