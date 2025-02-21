package shoes.pauline.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import shoes.pauline.entity.Shoe;

import java.util.List;

@Repository
public interface ShoeRepository extends JpaRepository<Shoe, Long> {

    @Query("SELECT s FROM Shoe s ORDER BY s.createdAt DESC LIMIT 1")
    Shoe findLatestCreatedShoe();

    @Query("SELECT s FROM Shoe s ORDER BY s.price ASC")
    List<Shoe> findAllByOrderByPriceAsc();

    @Query("SELECT s FROM Shoe s ORDER BY s.price DESC")
    List<Shoe> findAllByOrderByPriceDesc();

    @Query("SELECT s FROM Shoe s ORDER BY s.name ASC")
    List<Shoe> findAllByOrderByNameAsc();

    @Query("SELECT s FROM Shoe s ORDER BY s.name DESC")
    List<Shoe> findAllByOrderByNameDesc();

}