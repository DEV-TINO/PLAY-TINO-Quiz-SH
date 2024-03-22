package Spring.SpringTino.controller;

import Spring.SpringTino.domain.DTO.RequestCommentDeleteDTO;
import Spring.SpringTino.domain.DTO.RequestCommentSaveDTO;
import Spring.SpringTino.domain.DTO.RequestCommentUpdateDTO;
import Spring.SpringTino.domain.DTO.ResponseCommentDTO;
import Spring.SpringTino.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
public class CommentController {

    CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }


//    @GetMapping("/problem/comment/{commentId}")
//    public ResponseCommentDTO readCommentId(@PathVariable UUID commentId) {
//        return commentService.readCommentId(commentId);
//    }
//
//    @GetMapping("/problem/comment/all/{userId}")
//    public List<ResponseCommentDTO> readCommentAll(@PathVariable UUID userId) {
//        return commentService.readCommentAll(userId);
//    }

    //좋아요순으로 전체조회
    @GetMapping("/problem/comment/heartCount/{userId}")
    public List<ResponseCommentDTO> readCommentHeartCount(@PathVariable UUID userId, @RequestParam(required = false, defaultValue="0") int page) {
        return commentService.readCommentHeartCount(userId, page, 10);
    }

    //최신순으로 전체조회
    @GetMapping("/problem/comment/uploadTime/{userId}")
    public List<ResponseCommentDTO> readCommentUploadTime(@PathVariable UUID userId, @RequestParam(required = false, defaultValue="0") int page) {
        return commentService.readCommentUploadTime(userId, page, 10);
    }

    //댓글 저장
    @PostMapping("/problem/comment")
    public ResponseEntity<Map<String, Object>> createComment(@RequestBody RequestCommentSaveDTO requestCommentSaveDTO) {
        UUID commentId = commentService.createComment(requestCommentSaveDTO);

        // HTTP 상태 반환
        HttpStatus httpStatus = (commentId != null) ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;

        // 메시지와 id 값 json 데이터로 반환
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("success", commentId != null);

        return ResponseEntity.status(httpStatus).body(requestMap);
    }

    //댓글 수정
    @PutMapping("/problem/comment")
    public ResponseEntity<Map<String, Object>> updateComment(@RequestBody RequestCommentUpdateDTO requestCommentUpdateDTO) {
        UUID commentId = commentService.updateComment(requestCommentUpdateDTO);

        // HTTP 상태 반환
        HttpStatus httpStatus = (commentId != null) ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;

        // 메시지와 id 값 json 데이터로 반환
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("success", commentId != null);

        return ResponseEntity.status(httpStatus).body(requestMap);
    }

    //댓글 삭제
    @DeleteMapping("/problem/comment")
    public ResponseEntity<Map<String, Object>> deleteComment(@RequestBody RequestCommentDeleteDTO requestCommentDeleteDTO) {
        UUID commentId = commentService.deleteComment(requestCommentDeleteDTO);

        // HTTP 상태 반환
        HttpStatus httpStatus = (commentId != null) ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;

        // 메시지와 id 값 json 데이터로 반환
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("success", commentId != null);

        return ResponseEntity.status(httpStatus).body(requestMap);
    }

}
