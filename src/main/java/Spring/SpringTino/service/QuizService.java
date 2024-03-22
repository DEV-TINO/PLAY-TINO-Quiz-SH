package Spring.SpringTino.service;

import Spring.SpringTino.Bean.*;
import Spring.SpringTino.domain.DTO.RequestQuizRankSaveDTO;
import Spring.SpringTino.domain.DTO.ResponseQuizGameDTO;
import Spring.SpringTino.domain.DTO.ResponseQuizRankDTO;
import Spring.SpringTino.domain.Quiz;
import Spring.SpringTino.domain.QuizGame;
import Spring.SpringTino.domain.QuizRank;
import aj.org.objectweb.asm.TypeReference;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.cglib.core.Local;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
public class QuizService {


    GetQuizDAOBean getQuizDAOBean;
    SaveQuizGameDAOBean saveQuizGameDAOBean;
    SaveQuizRankDAOBean saveQuizRankDAOBean;
    GetQuizGameDAOBean getQuizGameDAOBean;
    GetQuizRanksDAOBean getQuizRanksDAOBean;
    GetQuizRankDAOBean getQuizRankDAOBean;
    GetQuizCommonsenseDAOBean getQuizCommonsenseDAOBean;
    GetQuizNonsenseDAOBean getQuizNonsenseDAOBean;
    ConvertListToString convertListToString;
    ConvertStringToList convertStringToList;

    public QuizService(ConvertStringToList convertStringToList, ConvertListToString convertListToString, GetQuizRankDAOBean getQuizRankDAOBean, GetQuizNonsenseDAOBean getQuizNonsenseDAOBean, GetQuizDAOBean getQuizDAOBean, GetQuizRanksDAOBean getQuizRanksDAOBean, SaveQuizGameDAOBean saveQuizGameDAOBean, SaveQuizRankDAOBean saveQuizRankDAOBean, GetQuizGameDAOBean getQuizGameDAOBean, GetQuizCommonsenseDAOBean getQuizCommonsenseDAOBean) {
        this.getQuizDAOBean = getQuizDAOBean;
        this.getQuizGameDAOBean = getQuizGameDAOBean;
        this.getQuizRanksDAOBean = getQuizRanksDAOBean;
        this.saveQuizGameDAOBean = saveQuizGameDAOBean;
        this.saveQuizRankDAOBean = saveQuizRankDAOBean;
        this.getQuizCommonsenseDAOBean = getQuizCommonsenseDAOBean;
        this.getQuizNonsenseDAOBean = getQuizNonsenseDAOBean;
        this.getQuizRankDAOBean = getQuizRankDAOBean;
        this.convertListToString = convertListToString;
        this.convertStringToList = convertStringToList;
    }

    // 게임 들어갈 때 문제 ID 생성해주고, Quiz 리스트를 반환해줌
    public ResponseQuizGameDTO readQuiz(UUID gameId, UUID userId){
        //새로고침 할때(게임 아이디가 존재)
        if (gameId != null){
            //gameId와 userId를 통해 QuizGame 객체를 찾는다
            QuizGame quizGame = getQuizGameDAOBean.exec(gameId, userId);

            // DTO 객체를 만들고 찾은 DAO 객체로 초기화
            ResponseQuizGameDTO responseQuizGameDTO = new ResponseQuizGameDTO();

            responseQuizGameDTO.setGameId(quizGame.getGameId());
            responseQuizGameDTO.setUserId(quizGame.getUserId());
            responseQuizGameDTO.setResponseQuizList(quizGame.getQuizList());

            //DTO 객체를 반환
            return responseQuizGameDTO;
        }

        //

        //퀴즈 10개를 랜덤으로 저장
        List<Quiz> QuizList = getQuizDAOBean.exec();

        // QuizGame 객체를 만들고 초기화
        QuizGame quizGame = new QuizGame();
        quizGame.setUserId(userId);

        //List인 퀴즈들을 String으로 변환
        String stringQuizList = convertListToString.exec(QuizList);

        quizGame.setQuizList(stringQuizList);
        quizGame.setCreateAt(LocalDateTime.now());

        saveQuizGameDAOBean.exec(quizGame);

        // ResponseQuizGameDTO를 만들고 setter로 저장시키고 반환하면 끝
        ResponseQuizGameDTO responseQuizGameDTO = new ResponseQuizGameDTO();
        responseQuizGameDTO.setGameId(quizGame.getGameId());
        responseQuizGameDTO.setUserId(quizGame.getUserId());
        responseQuizGameDTO.setResponseQuizList(stringQuizList);

        return responseQuizGameDTO;
    }




    //랭킹 저장하기
    public UUID saveQuizRank(RequestQuizRankSaveDTO requestQuizRankDTO) {
        //gameId와 userId를 통해 이미 랭킹에 저장되어 있으면 null 값 반환
        if(getQuizRankDAOBean.exec(requestQuizRankDTO.getGameId(), requestQuizRankDTO.getUserId()) != null)
            return null;

        //gameId와 userId로 quizGame 객체 찾기
        QuizGame quizGame = getQuizGameDAOBean.exec(requestQuizRankDTO.getGameId(), requestQuizRankDTO.getUserId());


        /*//DTO의 값들을 DAO에 저장
        quizGame.setGameId(requestQuizRankDTO.getGameId());
        quizGame.setUserId(requestQuizRankDTO.getUserId());*/

        // QuizRank 객체 생성 및 초기화
        QuizRank quizRank = new QuizRank();

        quizRank.setGameId(requestQuizRankDTO.getGameId());
        quizRank.setUserId(requestQuizRankDTO.getUserId());
        quizRank.setUserName(requestQuizRankDTO.getUserName());
        quizRank.setUpdateAt(LocalDateTime.now());

        //String으로 저장된 10개의 문제를 다시 List로 전환
        List<Quiz> quizList = convertStringToList.exec(quizGame.getQuizList());

        //상식 맞춘 개수
        int commonsenseCorrect = getQuizCommonsenseDAOBean.exec(quizList);
        quizRank.setCommonsenseCorrect(commonsenseCorrect);

        //넌센스 맞춘 개수
        int nonsenseCorrect = getQuizNonsenseDAOBean.exec(quizList);
        quizRank.setNonsenseCorrect(nonsenseCorrect);

        //상식, 넌센스 맞춘 개수의 합
        quizRank.setAllCorrect(commonsenseCorrect + nonsenseCorrect);

        //quizGame과 quizRank를 repository에 저장
        saveQuizGameDAOBean.exec(quizGame);
        saveQuizRankDAOBean.exec(quizRank);

        //키값 반환
        return quizGame.getGameId();
    }

    //랭킹 전체 조회
        public List<ResponseQuizRankDTO> readQuizRank(int page, int size) {
            //QuizRank 리스트를 맞춘개수 내림차순으로 페이징(5개씩)/정렬해 넣어둠
            Pageable pageable = PageRequest.of(page, size);
            Page<QuizRank> quizRankList = getQuizRanksDAOBean.exec(pageable);

            //DTO 리스트 생성
            List<ResponseQuizRankDTO> responseQuizRankDTOList = new ArrayList<>();

            //for문을 통해 각 QuizRank객체를 DTO 리스트에 저장해주는 로직
            for (QuizRank quizRank : quizRankList) {

                //각 QuizRank를 객체를 담아줄 DTO 객체를 생성하고 초기화
                ResponseQuizRankDTO responseQuizRankDTO = new ResponseQuizRankDTO();
                responseQuizRankDTO.setQuizRankId(quizRank.getQuizRankId());
                responseQuizRankDTO.setNonsenseCorrect(quizRank.getNonsenseCorrect());
                responseQuizRankDTO.setCommonsenseCorrect(quizRank.getCommonsenseCorrect());
                responseQuizRankDTO.setAllCorrect(quizRank.getAllCorrect());
                responseQuizRankDTO.setUserName(quizRank.getUserName());

                //DTO리스트에 각 DTO 저장
                responseQuizRankDTOList.add(responseQuizRankDTO);
            }

            //DTO 리스트 반환
            return responseQuizRankDTOList;

    }

}
