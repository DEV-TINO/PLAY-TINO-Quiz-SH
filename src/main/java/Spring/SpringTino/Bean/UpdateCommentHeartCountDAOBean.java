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
    public Comment heartCountUp(UUID commentId){

        //commentId로 해당 Comment(DAO) 찾기
        Comment comment = jpaCommentRepository.findById(commentId).get();

        //DAO의 댓글좋아요개수(HeartCount) +1
        comment.setHeartCount(comment.getHeartCount()+1);

        // 필드로 좋아요 여부를 판단해

        // 이 판단한 여부를 다른사람이랑 다르게할수 있는 방법이 없음



        return comment;

    }

    //댓글좋아요 삭제(-1)
    public Comment heartCountDown(UUID commentId){

        //commentId로 해당 Comment(DAO) 찾기
        Comment comment = jpaCommentRepository.findById(commentId).get();

        //댓글좋아요개수(HeartCount) -1
        comment.setHeartCount(comment.getHeartCount()-1);

        return comment;

    }

}
