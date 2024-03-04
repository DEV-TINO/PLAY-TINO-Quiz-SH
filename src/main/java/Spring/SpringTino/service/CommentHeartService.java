package Spring.SpringTino.service;

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
    CommentService commentService;

    @Autowired
    public CommentHeartService(JpaCommentHeartRepository jpaCommentHeartRepository, CommentService commentService) {
        this.jpaCommentHeartRepository = jpaCommentHeartRepository;
        this.commentService = commentService;
    }

    public UUID saveCommentHeart(RequestCommentHeartSaveDTO requestCommentHeartSaveDTO) {
        //댓글창 하트를 저장하는 로직을 구현
        //DTO를 통해서 DAO를 만들어줌
        CommentHeart commentHeart = new CommentHeart();

        //DAO값 초기화
        commentHeart.setCommentId(requestCommentHeartSaveDTO.getCommentId());
        commentHeart.setUserId(requestCommentHeartSaveDTO.getUserId());

        commentService.heartPlus(requestCommentHeartSaveDTO.getCommentId());

        //DAO값을 저장
        jpaCommentHeartRepository.save(commentHeart);

        //키값을 반환
        return commentHeart.getCommentHeartId();
    }

    public UUID deleteCommentHeart(RequestCommentHeartDeleteDTO requestCommentHeartDeleteDTO) {
        //댓글창 하트를 삭제하는 로직을 구현
        //유저 id와 comment id를 통해 삭제하길 원하는 comment를 찾는다
        CommentHeart commentHeart = jpaCommentHeartRepository.findByCommentIdAndUserId(
                requestCommentHeartDeleteDTO.getCommentId(), requestCommentHeartDeleteDTO.getUserId());



        commentService.heartMinus(requestCommentHeartDeleteDTO.getCommentId());

        jpaCommentHeartRepository.delete(commentHeart);

        return commentHeart.getCommentHeartId();
    }
}
