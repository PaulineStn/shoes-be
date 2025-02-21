package shoes.pauline.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import shoes.pauline.entity.Shoe;
import shoes.pauline.repository.ShoeRepository;

import java.util.List;
import java.util.Optional;

@Service
public class ShoeService {

    @Autowired
    private ShoeRepository shoeRepository;

    public List<Shoe> getAllShoes() {
        return shoeRepository.findAll();
    }

    public Optional<Shoe> getShoeById(Long id) {
        return shoeRepository.findById(id);
    }

    public Shoe createShoe(Shoe shoe) {
        return shoeRepository.save(shoe);
    }

    public Shoe updateShoe(Long id, Shoe updatedShoe) {
        return shoeRepository.findById(id).map(shoe -> {
            shoe.setName(updatedShoe.getName());
            shoe.setDescription(updatedShoe.getDescription());
            shoe.setPrice(updatedShoe.getPrice());
            shoe.setStock(updatedShoe.getStock());
            return shoeRepository.save(shoe);
        }).orElseThrow(() -> new RuntimeException("Shoe not found with id " + id));
    }

    public Shoe getLatestCreatedShoe() {
        return shoeRepository.findLatestCreatedShoe();
    }

    public List<Shoe> getShoesSortedByPriceAsc() {
        return shoeRepository.findAllByOrderByPriceAsc();
    }

    public List<Shoe> getShoesSortedByNameAsc() {
        return shoeRepository.findAllByOrderByNameAsc();
    }

    public List<Shoe> getShoesSortedByPriceDesc() {
        return shoeRepository.findAllByOrderByPriceDesc();
    }

    public List<Shoe> getShoesSortedByNameDesc() {
        return shoeRepository.findAllByOrderByNameDesc();
    }

    public void deleteShoe(Long id) {
        shoeRepository.deleteById(id);
    }

}