package Spring.SpringTino.repository;

import Spring.SpringTino.domain.Quiz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface JpaQuizRepository extends JpaRepository<Quiz, UUID> {
//    List<Quiz> findAllByCategory(String category);

    @Query(value = "select *  from Quiz order by rand() limit 5",nativeQuery = true)
    List<Quiz> findAllByCategory(String category);
}
