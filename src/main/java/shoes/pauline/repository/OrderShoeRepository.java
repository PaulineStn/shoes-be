package shoes.pauline.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shoes.pauline.entity.OrderShoe;

public interface OrderShoeRepository extends JpaRepository<OrderShoe, Long> {
}