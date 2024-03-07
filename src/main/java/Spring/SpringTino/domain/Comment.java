package Spring.SpringTino.domain;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    @Id @GeneratedValue(strategy= GenerationType.AUTO)
    UUID commentId;
    UUID userId;
    String content;
    Integer heartCount;
    LocalDateTime uploadTime;
}
