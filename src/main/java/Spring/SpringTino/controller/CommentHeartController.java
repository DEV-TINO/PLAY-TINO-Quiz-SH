package Spring.SpringTino.controller;

import Spring.SpringTino.domain.CommentHeart;
import Spring.SpringTino.domain.DTO.RequestCommentHeartDeleteDTO;
import Spring.SpringTino.domain.DTO.RequestCommentHeartSaveDTO;
import Spring.SpringTino.service.CommentHeartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane;
import java.io.Console;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class CommentHeartController {

    CommentHeartService commentHeartService;

    @Autowired
    public CommentHeartController(CommentHeartService commentHeartService) {
        this.commentHeartService = commentHeartService;
    }

    @PostMapping("/problem/commentHeart")
    public ResponseEntity<Map<String, Object>> saveCommentHeart(@RequestBody RequestCommentHeartSaveDTO requestCommentHeartSaveDTO) {
        UUID commentHeartId = commentHeartService.saveCommentHeart(requestCommentHeartSaveDTO);

        HttpStatus httpStatus = (commentHeartId != null) ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;

        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("message", (commentHeartId != null) ? "좋아요 추가 성공" : "좋아요 추가 실패");

        return ResponseEntity.status(httpStatus).body(requestMap);
    }

    @DeleteMapping("/problem/commentHeart")
    public ResponseEntity<Map<String, Object>> deleteCommentHeart(@RequestBody RequestCommentHeartDeleteDTO requestCommentHeartDeleteDTO) {
        UUID commentHeartId = commentHeartService.deleteCommentHeart(requestCommentHeartDeleteDTO);

        HttpStatus httpStatus = (commentHeartId != null) ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;

        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("message", (commentHeartId != null) ? "좋아요 삭제 성공" : "좋아요 삭제 실패");

        return ResponseEntity.status(httpStatus).body(requestMap);
    }
}
