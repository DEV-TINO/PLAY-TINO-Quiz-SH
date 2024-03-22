package Spring.SpringTino.service;

import Spring.SpringTino.Bean.*;
import Spring.SpringTino.domain.*;
import Spring.SpringTino.domain.DTO.RequestCommentDeleteDTO;
import Spring.SpringTino.domain.DTO.RequestCommentSaveDTO;
import Spring.SpringTino.domain.DTO.RequestCommentUpdateDTO;
import Spring.SpringTino.domain.DTO.ResponseCommentDTO;
import Spring.SpringTino.repository.JpaCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class CommentService {

    SaveCommentDAOBean saveCommentDAOBean;
    DeleteCommentDAOBean deleteCommentDAOBean;
    CheckCommentHeartDAOBean checkCommentHeartDAOBean;
    GetCommentDAOBean getCommentDAOBean;
    GetCommentsDAOBean getCommentsDAOBean;

    @Autowired
    public CommentService(SaveCommentDAOBean saveCommentDAOBean, DeleteCommentDAOBean deleteCommentDAOBean, CheckCommentHeartDAOBean checkCommentHeartDAOBean, GetCommentDAOBean getCommentDAOBean, GetCommentsDAOBean getCommentsDAOBean) {
        this.saveCommentDAOBean = saveCommentDAOBean;
        this.deleteCommentDAOBean = deleteCommentDAOBean;
        this.checkCommentHeartDAOBean = checkCommentHeartDAOBean;
        this.getCommentDAOBean = getCommentDAOBean;
        this.getCommentsDAOBean = getCommentsDAOBean;
    }


//    //commentId를 통한 댓글 조회
//    public ResponseCommentDTO readCommentId(UUID commentId) {
//        // id를 통해서 comment 객체를 찾아
//        // findById -> Pk값을 통해서 해당 객체를 찾아서 반환해줌
//        Comment comment = jpaCommentRepository.findById(commentId).get();
//
//
//        // 찾은 객체는 DAO 임
//        // DAO -> DTO로 변환하는 과정
//        ResponseCommentDTO responseCommentDTO = new ResponseCommentDTO();
//
//        // DTO 객체 값을 초기화
//        responseCommentDTO.setCommentId(comment.getCommentId());
//        responseCommentDTO.setUserId(comment.getUserId());
//        responseCommentDTO.setHeartCount(comment.getHeartCount());
//        responseCommentDTO.setUploadTime(comment.getUploadTime());
//        responseCommentDTO.setContent(comment.getContent());
//        responseCommentDTO.setCheckMyHeart(checkCommentHeartDAOBean.checkMyHeart(comment.getCommentId(), comment.getUserId()));
//
//        // 생성된 DTO를 반환
//        return responseCommentDTO;
//    }

//    //댓글 전체 조회
//    public List<ResponseCommentDTO> readCommentAll(UUID userId) {
//        //List를 통해
//        List<Comment> comments = jpaCommentRepository.findAll();
//
//        List<ResponseCommentDTO> responseCommentDTOS = new ArrayList<>();
//
//        for(Comment comment : comments) {
//            ResponseCommentDTO responseCommentDTO = new ResponseCommentDTO();
//
//            responseCommentDTO.setCommentId(comment.getCommentId());
//            responseCommentDTO.setUserId(comment.getUserId());
//            responseCommentDTO.setHeartCount(comment.getHeartCount());
//            responseCommentDTO.setUploadTime(comment.getUploadTime());
//            responseCommentDTO.setContent(comment.getContent());
//            responseCommentDTO.setCheckMyHeart(checkCommentHeartDAOBean.checkMyHeart(comment.getCommentId(), userId));
//
//
//            responseCommentDTOS.add(responseCommentDTO);
//        }
//
//
//        return responseCommentDTOS;
//    }

    //인기순 전체조회
    public List<ResponseCommentDTO> readCommentHeartCount(UUID userId, int page, int size) {
        //하트 갯수에 따라 내림차순으로 찾아주고 댓글 10개씩 페이징 처리해 comments 리스트에 저장
        Pageable pageable = PageRequest.of(page, size);
        Page<Comment> comments = getCommentsDAOBean.execHeartCount(pageable);

        // DAO 객체들을 저장할 DTO 리스트 생성
        List<ResponseCommentDTO> responseCommentDTOS = new ArrayList<>();

        //각 리스트 객체마다 DTO를 초기화 시켜준다.
        for(Comment comment : comments) {
            //DTO 객체 생성
            ResponseCommentDTO responseCommentDTO = new ResponseCommentDTO();

            //DTO 객체 초기화
            responseCommentDTO.setCommentId(comment.getCommentId());
            responseCommentDTO.setUserId(comment.getUserId());
            responseCommentDTO.setHeartCount(comment.getHeartCount());
            responseCommentDTO.setUploadTime(comment.getUploadTime());
            responseCommentDTO.setContent(comment.getContent());
            //자신이 좋아요를 눌렀던 댓글은 true, 누르지 않았던 댓글은 false를 반환해주는 boolean값
            responseCommentDTO.setCheckMyHeart(checkCommentHeartDAOBean.checkMyHeart(comment.getCommentId(), userId));

            //DTO 리스트에 각 DTO 객체 저장
            responseCommentDTOS.add(responseCommentDTO);
        }

        //DTO 리스트 반환
        return responseCommentDTOS;
    }

    //최신순 전체조회
    public List<ResponseCommentDTO> readCommentUploadTime(UUID userId, int page, int size) {
        //업로드 날짜에 따라 내림차순으로 찾아주고 댓글 10개씩 페이징 처리해 comments 리스트에 저장
        Pageable pageable = PageRequest.of(page, size);
        Page<Comment> comments = getCommentsDAOBean.execUpLoadTime(pageable);

        //DAO -> DTO
        List<ResponseCommentDTO> responseCommentDTOS = new ArrayList<>();

        //각 리스트 객체마다 DTO를 초기화 시켜준다.
        for(Comment comment : comments) {
            //DTO 객체 생성
            ResponseCommentDTO responseCommentDTO = new ResponseCommentDTO();

            //DTO 객체 초기화
            responseCommentDTO.setCommentId(comment.getCommentId());
            responseCommentDTO.setUserId(comment.getUserId());
            responseCommentDTO.setHeartCount(comment.getHeartCount());
            responseCommentDTO.setUploadTime(comment.getUploadTime());
            responseCommentDTO.setContent(comment.getContent());

            //자신이 좋아요를 눌렀던 댓글은 true, 누르지 않았던 댓글은 false를 반환해주는 boolean값
            responseCommentDTO.setCheckMyHeart(checkCommentHeartDAOBean.checkMyHeart(comment.getCommentId(), userId));

            //각 DTO 객체를 DTO 리스트에 저장
            responseCommentDTOS.add(responseCommentDTO);
        }

        //DTO 리스트 반환
        return responseCommentDTOS;
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
        saveCommentDAOBean.exec(comment);

        // 만들어진 Comment에 PK값을 반환해준다.
        return comment.getCommentId();
    }

    //댓글 수정
    public UUID updateComment(RequestCommentUpdateDTO requestCommentUpdateDTO) {
        //commentId와 userId 통해서 원하는 comment 객체를 찾는다
        Comment comment = getCommentDAOBean.exec(requestCommentUpdateDTO.getCommentId(), requestCommentUpdateDTO.getUserId());

        //comment(DAO)객체 안에 requestCommentUpdateDTO 의 값을 넣어준다
        comment.setUserId(requestCommentUpdateDTO.getUserId());
        comment.setCommentId(requestCommentUpdateDTO.getCommentId());
        comment.setContent(requestCommentUpdateDTO.getContent());

        //DAO를 저장
        saveCommentDAOBean.exec(comment);

        //DAO의 키값을 반환
        return comment.getCommentId();
    }

    //댓글 삭제
    public UUID deleteComment(RequestCommentDeleteDTO requestCommentDeleteDTO) {
        //commentId와 userId 통해서 원하는 comment 객체를 찾는다
        Comment comment = getCommentDAOBean.exec(requestCommentDeleteDTO.getCommentId(), requestCommentDeleteDTO.getUserId());

        //댓글(DAO)을 삭제
        deleteCommentDAOBean.exec(comment);

        //DAO의 키값을 반환
        return comment.getCommentId();
    }

}
