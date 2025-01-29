package shoes.pauline.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "reviews")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Review {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long reviewId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    @JsonIgnore
    private User user;

    @ManyToOne
    @JoinColumn(name = "shoe_id", nullable = false)
    @JsonIgnore
    private Shoe shoe;

    @Column(nullable = false)
    private Integer rating;

    private String comment;

    @Column(nullable = false)
    private LocalDateTime reviewDate = LocalDateTime.now();
}