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

    GetCommentHeartDAOBean getCommentHeartDAOBean;
    GetCommentDAOBean getCommentDAOBean;
    UpdateCommentHeartCountDAOBean updateCommentHeartCountDAOBean;
    SaveCommentHeartDAOBean saveCommentHeartDAOBean;
    SaveCommentDAOBean saveCommentDAOBean;
    DeleteCommentHeartDAOBean deleteCommentHeartDAOBean;
    DeleteCommentDAOBean deleteCommentDAOBean;
    CheckCommentHeartDAOBean checkCommentHeartDAOBean;

    @Autowired
    public CommentHeartService(GetCommentHeartDAOBean getCommentHeartDAOBean, GetCommentDAOBean getCommentDAOBean, UpdateCommentHeartCountDAOBean updateCommentHeartCountDAOBean, SaveCommentHeartDAOBean saveCommentHeartDAOBean, DeleteCommentHeartDAOBean deleteCommentHeartDAOBean, SaveCommentDAOBean saveCommentDAOBean, DeleteCommentDAOBean deleteCommentDAOBean, CheckCommentHeartDAOBean checkCommentHeartDAOBean) {
        this.getCommentHeartDAOBean = getCommentHeartDAOBean;
        this.getCommentDAOBean = getCommentDAOBean;
        this.updateCommentHeartCountDAOBean = updateCommentHeartCountDAOBean;
        this.saveCommentHeartDAOBean = saveCommentHeartDAOBean;
        this.deleteCommentHeartDAOBean = deleteCommentHeartDAOBean;
        this.deleteCommentDAOBean = deleteCommentDAOBean;
        this.saveCommentDAOBean = saveCommentDAOBean;
        this.checkCommentHeartDAOBean = checkCommentHeartDAOBean;
    }

    //댓글창 하트를 저장
    public UUID saveCommentHeart(RequestCommentHeartSaveDTO requestCommentHeartSaveDTO) {

        //이미 좋아요되어있으면 null 반환
        if (getCommentHeartDAOBean.exec(requestCommentHeartSaveDTO.getCommentId(), requestCommentHeartSaveDTO.getUserId()) != null)
            return null;

        //commentHeart 객체 생성
        //DTO를 통해서 DAO를 만들어줌
        CommentHeart commentHeart = new CommentHeart();

        //coommentHeart(DAO)값 초기화
        commentHeart.setCommentId(requestCommentHeartSaveDTO.getCommentId());
        commentHeart.setUserId(requestCommentHeartSaveDTO.getUserId());

        //commentId를 통해 원하는 comment 객체 찾기
        Comment comment = getCommentDAOBean.exec(requestCommentHeartSaveDTO.getCommentId());
        if (comment == null)    return null;

        //찾은 comment 객체 좋아요 추가
        updateCommentHeartCountDAOBean.heartCountUp(comment);

        //댓글 좋아요를 저장
        saveCommentHeartDAOBean.exec(commentHeart);

        //바뀐 댓글을 저장
        saveCommentDAOBean.exec(comment);

        //키값을 반환
        return commentHeart.getCommentHeartId();
    }


    //댓글창 하트를 삭제
    public UUID deleteCommentHeart(RequestCommentHeartDeleteDTO requestCommentHeartDeleteDTO) {
        //commentId와 UserId로 해당 CommentHeart(DAO) 찾기
        CommentHeart commentHeart = getCommentHeartDAOBean.exec(requestCommentHeartDeleteDTO.getCommentId(), requestCommentHeartDeleteDTO.getUserId());

        //좋아요를 누른적 없으면 null값 반환
        if (commentHeart == null) return null;

        //commentId를 통해 원하는 comment 객체 찾기
        Comment comment = getCommentDAOBean.exec(requestCommentHeartDeleteDTO.getCommentId());
        if (comment == null)    return null;

        //찾은 comment 객체 좋아요 삭제
        updateCommentHeartCountDAOBean.heartCountDown(comment);

        //댓글 좋아요(DAO)값을 삭제
        deleteCommentHeartDAOBean.exec(commentHeart);

        // 바뀐 댓글을 저장
        saveCommentDAOBean.exec(comment);

        //키값을 반환
        return commentHeart.getCommentHeartId();
    }


}
