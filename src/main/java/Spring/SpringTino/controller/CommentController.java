package Spring.SpringTino.controller;

import Spring.SpringTino.domain.DTO.RequestCommentDeleteDTO;
import Spring.SpringTino.domain.DTO.RequestCommentSaveDTO;
import Spring.SpringTino.domain.DTO.RequestCommentUpdateDTO;
import Spring.SpringTino.domain.DTO.ResponseCommentDTO;
import Spring.SpringTino.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class CommentController {

    CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/comment/{commentId}")
    public ResponseCommentDTO readCommentId(@PathVariable UUID commentId) {

        ResponseCommentDTO responseCommentDTO = commentService.readCommentId(commentId);
        if (responseCommentDTO ==null){
            // 에러로직 작성
        }
        return responseCommentDTO;
    }

    @GetMapping("/comment/all")
    public List<ResponseCommentDTO> readCommentAll() {
        return commentService.readCommentAll();
    }

    @PostMapping("/comment")
    public UUID createComment(@RequestBody RequestCommentSaveDTO requestCommentSaveDTO) {
        return commentService.createComment(requestCommentSaveDTO);
    }

    @PutMapping("/comment")
    public UUID updateComment(@RequestBody RequestCommentUpdateDTO requestCommentUpdateDTO) {
        return commentService.updateComment(requestCommentUpdateDTO);
    }

    @DeleteMapping("/comment")
    public UUID deleteComment(@RequestBody RequestCommentDeleteDTO requestCommentDeleteDTO) {
        return commentService.deleteComment(requestCommentDeleteDTO);
    }

}
