package Spring.SpringTino.service;

import Spring.SpringTino.Bean.*;
import Spring.SpringTino.domain.Comment;
import Spring.SpringTino.domain.CommentHeart;
import Spring.SpringTino.domain.DTO.RequestCommentHeartDeleteDTO;
import Spring.SpringTino.domain.DTO.RequestCommentHeartSaveDTO;
import Spring.SpringTino.repository.JpaCommentHeartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CommentHeartService {

    JpaCommentHeartRepository jpaCommentHeartRepository;
    UpdateCommentHeartCountDAOBean updateCommentHeartCountDAOBean;
    SaveCommentHeartDAOBean saveCommentHeartDAOBean;
    SaveCommentDAOBean saveCommentDAOBean;
    DeleteCommentHeartDAOBean deleteCommentHeartDAOBean;
    DeleteCommentDAOBean deleteCommentDAOBean;

    CheckCommentHeartDAOBean checkCommentHeartDAOBean;

    @Autowired
    public CommentHeartService(JpaCommentHeartRepository jpaCommentHeartRepository, UpdateCommentHeartCountDAOBean updateCommentHeartCountDAOBean, SaveCommentHeartDAOBean saveCommentHeartDAOBean, DeleteCommentHeartDAOBean deleteCommentHeartDAOBean, SaveCommentDAOBean saveCommentDAOBean, DeleteCommentDAOBean deleteCommentDAOBean, CheckCommentHeartDAOBean checkCommentHeartDAOBean) {
        this.jpaCommentHeartRepository = jpaCommentHeartRepository;
        this.updateCommentHeartCountDAOBean = updateCommentHeartCountDAOBean;
        this.saveCommentHeartDAOBean = saveCommentHeartDAOBean;
        this.deleteCommentHeartDAOBean = deleteCommentHeartDAOBean;
        this.deleteCommentDAOBean = deleteCommentDAOBean;
        this.saveCommentDAOBean = saveCommentDAOBean;
        this.checkCommentHeartDAOBean = checkCommentHeartDAOBean;
    }

    //댓글창 하트를 저장
    public UUID saveCommentHeart(RequestCommentHeartSaveDTO requestCommentHeartSaveDTO) {
        //commentHeart 객체 생성
        //DTO를 통해서 DAO를 만들어줌
        CommentHeart commentHeart = new CommentHeart();

        //coommentHeart(DAO)값 초기화
        commentHeart.setCommentId(requestCommentHeartSaveDTO.getCommentId());
        commentHeart.setUserId(requestCommentHeartSaveDTO.getUserId());

        //이미 좋아요되어있으면 에러 반환
        if (jpaCommentHeartRepository.findByCommentIdAndUserId(
                requestCommentHeartSaveDTO.getCommentId(), requestCommentHeartSaveDTO.getUserId()) != null)
            return null;

        //좋아요 추가
        Comment comment = updateCommentHeartCountDAOBean.heartCountUp(requestCommentHeartSaveDTO.getCommentId());

        //댓글 좋아요(DAO)값을 저장
        saveCommentHeartDAOBean.exec(commentHeart);
        saveCommentDAOBean.exec(comment);

        //키값을 반환
        return commentHeart.getCommentHeartId();
    }


    //댓글창 하트를 삭제
    public UUID deleteCommentHeart(RequestCommentHeartDeleteDTO requestCommentHeartDeleteDTO) {
        //commentId와 UserId로 해당 CommentHeart(DAO) 찾기
        CommentHeart commentHeart = jpaCommentHeartRepository.findByCommentIdAndUserId(
                requestCommentHeartDeleteDTO.getCommentId(), requestCommentHeartDeleteDTO.getUserId());


        if(jpaCommentHeartRepository.findByCommentIdAndUserId(
                requestCommentHeartDeleteDTO.getCommentId(), requestCommentHeartDeleteDTO.getUserId()) == null)
            return null;


        //좋아요 삭제
        Comment comment = updateCommentHeartCountDAOBean.heartCountDown(requestCommentHeartDeleteDTO.getCommentId());

        //댓글 좋아요(DAO)값을 삭제
        deleteCommentHeartDAOBean.exec(commentHeart);
        deleteCommentDAOBean.exec(comment);

        //키값을 반환
        return commentHeart.getCommentHeartId();
    }


}
