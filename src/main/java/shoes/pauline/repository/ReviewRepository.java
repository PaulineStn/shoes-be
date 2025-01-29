package shoes.pauline.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shoes.pauline.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Long> {
}