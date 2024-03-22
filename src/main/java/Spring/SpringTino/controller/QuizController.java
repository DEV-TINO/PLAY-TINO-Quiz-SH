package Spring.SpringTino.controller;

import Spring.SpringTino.domain.DTO.RequestQuizRankSaveDTO;
import Spring.SpringTino.domain.DTO.ResponseQuizGameDTO;
import Spring.SpringTino.domain.DTO.ResponseQuizRankDTO;
import Spring.SpringTino.domain.QuizRank;
import Spring.SpringTino.service.QuizService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
public class QuizController {
    QuizService quizService;

    @Autowired
    public QuizController(QuizService quizService) {
        this.quizService = quizService;
    }

    //
    @GetMapping("/problem/all/{userId}")
    @ResponseBody
    public ResponseQuizGameDTO readQuizAll(@PathVariable UUID userId, @RequestParam(required = false) UUID gameId) {
        return quizService.readQuiz(gameId, userId);
    }

    @GetMapping("/problem/rank-all")
    public List<ResponseQuizRankDTO> readQuizRank(@RequestParam(required = false, defaultValue="0") int page) {
        return quizService.readQuizRank(page, 5);
    }

    //
    @PostMapping("/problem/rank")
    public ResponseEntity<Map<String, Object>> saveCommentHeart(@RequestBody RequestQuizRankSaveDTO requestQuizRankDTO) {
        UUID gameId = quizService.saveQuizRank(requestQuizRankDTO);

        HttpStatus httpStatus = (gameId != null) ? HttpStatus.OK : HttpStatus.INTERNAL_SERVER_ERROR;

        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("message", gameId != null);

        return ResponseEntity.status(httpStatus).body(requestMap);
    }

}
