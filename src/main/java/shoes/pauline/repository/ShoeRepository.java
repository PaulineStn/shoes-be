package shoes.pauline.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import shoes.pauline.entity.Shoe;

@Repository
public interface ShoeRepository extends JpaRepository<Shoe, Long> {

    @Query("SELECT s FROM Shoe s WHERE s.shoeId = (SELECT MAX(s2.shoeId) FROM Shoe s2)")
    Shoe findShoeWithMaxId();
}