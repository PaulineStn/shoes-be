package shoes.pauline.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shoes.pauline.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}