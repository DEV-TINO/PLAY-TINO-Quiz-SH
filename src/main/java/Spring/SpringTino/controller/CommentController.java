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


    @GetMapping("/problem/comment/{commentId}")
    public ResponseCommentDTO readCommentId(@PathVariable UUID commentId) {
        return commentService.readCommentId(commentId);
    }

//    @GetMapping("/problem/comment/heart")
//    public List<ResponseCommentDTO> readCommentHeart() {
//        return commentService.readCommentHeart();
//    }

    @GetMapping("/problem/comment/all/{userId}")
    public List<ResponseCommentDTO> readCommentAll(@PathVariable UUID userId) {
        return commentService.readCommentAll(userId);
    }

    @PostMapping("/problem/comment")
    public ResponseEntity<Map<String, Object>> createComment(@RequestBody RequestCommentSaveDTO requestCommentSaveDTO) {
        UUID commentId = commentService.createComment(requestCommentSaveDTO);

        // HTTP 상태 반환
        HttpStatus httpStatus = (commentId != null) ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;

        // 메시지와 id 값 json 데이터로 반환
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("message", (commentId != null) ? "생성 성공" : "생성 실패");

        return ResponseEntity.status(httpStatus).body(requestMap);
    }

    @PutMapping("/problem/comment")
    public ResponseEntity<Map<String, Object>> updateComment(@RequestBody RequestCommentUpdateDTO requestCommentUpdateDTO) {
//        return commentService.updateComment(requestCommentUpdateDTO);
        UUID commentId = commentService.updateComment(requestCommentUpdateDTO);

        // HTTP 상태 반환
        HttpStatus httpStatus = (commentId != null) ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;

        // 메시지와 id 값 json 데이터로 반환
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("message", (commentId != null) ? "수정 성공" : "수정 실패");

        return ResponseEntity.status(httpStatus).body(requestMap);
    }

    @DeleteMapping("/problem/comment")
    public ResponseEntity<Map<String, Object>> deleteComment(@RequestBody RequestCommentDeleteDTO requestCommentDeleteDTO) {
        UUID commentId = commentService.deleteComment(requestCommentDeleteDTO);

        // HTTP 상태 반환
        HttpStatus httpStatus = (commentId != null) ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;

        // 메시지와 id 값 json 데이터로 반환
        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("message", (commentId != null) ? "삭제 성공" : "삭제 실패");

        return ResponseEntity.status(httpStatus).body(requestMap);
    }

}
