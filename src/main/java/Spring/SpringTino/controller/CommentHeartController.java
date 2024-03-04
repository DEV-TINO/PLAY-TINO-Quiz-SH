package Spring.SpringTino.controller;

import Spring.SpringTino.domain.DTO.RequestCommentHeartDeleteDTO;
import Spring.SpringTino.domain.DTO.RequestCommentHeartSaveDTO;
import Spring.SpringTino.service.CommentHeartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
public class CommentHeartController {

    CommentHeartService commentHeartService;

    @Autowired
    public CommentHeartController(CommentHeartService commentHeartService) {
        this.commentHeartService = commentHeartService;
    }

    @PostMapping("/commentHeart")
    public  UUID saveCommentHeart(@RequestBody RequestCommentHeartSaveDTO requestCommentHeartSaveDTO) {
        return commentHeartService.saveCommentHeart(requestCommentHeartSaveDTO);
    }

    @DeleteMapping("/commentHeart")
    public UUID deleteCommentHeart(@RequestBody RequestCommentHeartDeleteDTO requestCommentHeartDeleteDTO) {
        return commentHeartService.deleteCommentHeart(requestCommentHeartDeleteDTO);
    }
}
