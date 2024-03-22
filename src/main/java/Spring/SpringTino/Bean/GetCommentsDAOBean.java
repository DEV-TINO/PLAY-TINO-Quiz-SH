package Spring.SpringTino.Bean;

import Spring.SpringTino.domain.Comment;
import Spring.SpringTino.repository.JpaCommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class GetCommentsDAOBean {
    JpaCommentRepository jpaCommentRepository;

    @Autowired
    public GetCommentsDAOBean(JpaCommentRepository jpaCommentRepository) {
        this.jpaCommentRepository = jpaCommentRepository;
    }

    //업로드 시간으로 내림차순하고 페이징 처리
    public Page<Comment> execUpLoadTime(Pageable pageable) {
        return jpaCommentRepository.findAllByOrderByUploadTimeDesc(pageable);
    }

    //하트 개수로 내림차순하고 페이징 처리
    public Page<Comment> execHeartCount(Pageable pageable) {
        return jpaCommentRepository.findAllByOrderByHeartCountDesc(pageable);
    }
}
