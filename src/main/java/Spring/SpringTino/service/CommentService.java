package Spring.SpringTino.service;

import Spring.SpringTino.domain.*;
import Spring.SpringTino.domain.DTO.RequestCommentDeleteDTO;
import Spring.SpringTino.domain.DTO.RequestCommentSaveDTO;
import Spring.SpringTino.domain.DTO.RequestCommentUpdateDTO;
import Spring.SpringTino.domain.DTO.ResponseCommentDTO;
import Spring.SpringTino.repository.JpaCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CommentService {

    JpaCommentRepository jpaCommentRepository;

    @Autowired
    public CommentService(JpaCommentRepository jpaCommentRepository) {
        this.jpaCommentRepository = jpaCommentRepository;
    }

    //댓글 생성
    public UUID createComment(RequestCommentSaveDTO requestCommentSaveDTO) {
        // 댓글을 생성하는 로직을 작성
        // DTO를 통해서 DAO를 만들어줌
        Comment comment = new Comment();

        // DAO 값 초기화
        comment.setUserId(requestCommentSaveDTO.getUserId());
        comment.setContent(requestCommentSaveDTO.getContent());
        comment.setHeartCount(0);
        comment.setUploadTime(LocalDateTime.now());

        // DAO를 저장 -> comment createComment
        jpaCommentRepository.save(comment);

        // 만들어진 Comment에 PK값을 반환해준다.
        return comment.getCommentId();
    }

    public List<ResponseCommentDTO> readCommentAll() {
        //List를 통해
        List<Comment> comments = jpaCommentRepository.findAll();


        List<ResponseCommentDTO> responseCommentDTOS = new ArrayList<>();

        for(Comment comment : comments) {
            ResponseCommentDTO responseCommentDTO = new ResponseCommentDTO();

            responseCommentDTO.setCommentId(comment.getCommentId());
            responseCommentDTO.setUserId(comment.getUserId());
            responseCommentDTO.setHeartCount(comment.getHeartCount());
            responseCommentDTO.setUploadTime(comment.getUploadTime());
            responseCommentDTO.setContent(comment.getContent());

            responseCommentDTOS.add(responseCommentDTO);
        }

        return responseCommentDTOS;
    }

    public ResponseCommentDTO readCommentId(UUID commentId) {
        // id를 통해서 comment 객체를 찾아
        // findById -> Pk값을 통해서 해당 객체를 찾아서 반환해줌
        Comment comment = jpaCommentRepository.findById(commentId).orElse(null);
        if (comment == null) return null;

        // 찾은 객체는 DAO 임
        // DAO -> DTO로 변환하는 과정
        ResponseCommentDTO responseCommentDTO = new ResponseCommentDTO();

        // DTO 객체 값을 초기화
        responseCommentDTO.setCommentId(comment.getCommentId());
        responseCommentDTO.setUserId(comment.getUserId());
        responseCommentDTO.setHeartCount(comment.getHeartCount());
        responseCommentDTO.setUploadTime(comment.getUploadTime());
        responseCommentDTO.setContent(comment.getContent());

        // 생성된 DTO를 반환
        return responseCommentDTO;
    }

    public UUID updateComment(RequestCommentUpdateDTO requestCommentUpdateDTO) {
        //id를 통해서 원하는 comment 객체를 찾는다
        Comment comment = jpaCommentRepository.findById(requestCommentUpdateDTO.getCommentId()).get();

        //DAO안에 DTO의 값을 넣어준다
        comment.setUserId(requestCommentUpdateDTO.getUserId());
        comment.setCommentId(requestCommentUpdateDTO.getCommentId());
        comment.setContent(requestCommentUpdateDTO.getContent());

        //DAO를 저장
        jpaCommentRepository.save(comment);

        //DAO의 키값을 반환
        return comment.getCommentId();
    }


    public UUID deleteComment(RequestCommentDeleteDTO requestCommentDeleteDTO) {
        Comment comment = jpaCommentRepository.findById(requestCommentDeleteDTO.getCommentId()).get();

        //DAO를 삭제
        jpaCommentRepository.delete(comment);

        //DAO의 키값을 반환
        return comment.getCommentId();
    }

    public void heartPlus(UUID commentId) {
        Comment comment = jpaCommentRepository.findById(commentId).get();

        comment.setHeartCount(comment.getHeartCount()+1);
    }

    public void heartMinus(UUID commentId) {
        Comment comment = jpaCommentRepository.findById(commentId).get();

        comment.setHeartCount(comment.getHeartCount()-1);
    }
}