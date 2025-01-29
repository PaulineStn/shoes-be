package shoes.pauline.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import shoes.pauline.entity.Shoe;

@Repository
public interface ShoeRepository extends JpaRepository<Shoe, Long> {

}