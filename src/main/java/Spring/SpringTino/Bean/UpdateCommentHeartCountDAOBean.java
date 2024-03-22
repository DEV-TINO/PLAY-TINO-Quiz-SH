package Spring.SpringTino.Bean;

import Spring.SpringTino.domain.Comment;
import Spring.SpringTino.repository.JpaCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class UpdateCommentHeartCountDAOBean {

    JpaCommentRepository jpaCommentRepository;

    @Autowired
    public UpdateCommentHeartCountDAOBean(JpaCommentRepository jpaCommentRepository){
        this.jpaCommentRepository = jpaCommentRepository;
    }

    //댓글좋아요 추가(+1)
    public Comment heartCountUp(Comment comment){
        //DAO의 댓글좋아요개수(HeartCount) +1
        comment.setHeartCount(comment.getHeartCount()+1);

        //comment 객체 반환
        return comment;

    }

    //댓글좋아요 삭제(-1)
    public Comment heartCountDown(Comment comment){
        //댓글좋아요개수(HeartCount) -1
        comment.setHeartCount(comment.getHeartCount()-1);

        //comment 객체 반환
        return comment;

    }

}
