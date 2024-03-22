package Spring.SpringTino.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class QuizGame {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    UUID gameId;
    UUID userId;
    String quizList;
    LocalDateTime createAt;
}
