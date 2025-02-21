package shoes.pauline.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shoes.pauline.entity.Shoe;
import shoes.pauline.service.ShoeService;

import java.util.List;

@RestController
@RequestMapping("/api/shoes")
@Tag(name = "Shoes", description = "Operations related to shoe management.")
public class ShoeController {

    @Autowired
    private ShoeService shoeService;

    @GetMapping
    @Operation(summary = "Get all shoes", description = "Retrieve a list of all available shoes.")
    public ResponseEntity<List<Shoe>> getAllShoes() {
        return ResponseEntity.ok(shoeService.getAllShoes());
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get shoe by ID", description = "Retrieve a specific shoe by its ID.")
    public ResponseEntity<Shoe> getShoeById(@PathVariable Long id) {
        return shoeService.getShoeById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/lastShoe")
    @Operation(summary = "Get latest shoe", description = "Retrieve the latest shoe posted")
    public ResponseEntity<Shoe> getLatestCreatedShoe() {
        return ResponseEntity.ok(shoeService.getLatestCreatedShoe());
    }

    @GetMapping("/priceAsc")
    @Operation(summary = "Sort the shoes by price asc", description = "")
    public ResponseEntity<List<Shoe>> getShoesSortedByPriceAsc() {
        return ResponseEntity.ok(shoeService.getShoesSortedByPriceAsc());
    }


    @GetMapping("/nameAsc")
    @Operation(summary = "Sort the shoes by name asc", description = "")
    public ResponseEntity<List<Shoe>> getShoesSortedByNameAsc() {
        return ResponseEntity.ok(shoeService.getShoesSortedByNameAsc());
    }


    @GetMapping("/priceDesc")
    @Operation(summary = "Sort the shoes by price Desc", description = "")
    public ResponseEntity<List<Shoe>> getShoesSortedByPriceDesc() {
        return ResponseEntity.ok(shoeService.getShoesSortedByPriceDesc());
    }


    @GetMapping("/nameDesc")
    @Operation(summary = "Sort the shoes by name Desc", description = "")
    public ResponseEntity<List<Shoe>> getShoesSortedByNameDesc() {
        return ResponseEntity.ok(shoeService.getShoesSortedByNameDesc());
    }

    @PostMapping
    @Operation(summary = "Create a new shoe", description = "Add a new shoe to the catalog.")
    public ResponseEntity<Shoe> createShoe(@Valid @RequestBody Shoe shoe) {
        return ResponseEntity.ok(shoeService.createShoe(shoe));
    }

    @PutMapping("/{id}")
    @Operation(summary = "Update a shoe", description = "Update details of an existing shoe.")
    public ResponseEntity<Shoe> updateShoe(@PathVariable Long id, @Valid @RequestBody Shoe shoe) {
        return ResponseEntity.ok(shoeService.updateShoe(id, shoe));
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a shoe", description = "Remove a shoe from the catalog.")
    public ResponseEntity<Void> deleteShoe(@PathVariable Long id) {
        shoeService.deleteShoe(id);
        return ResponseEntity.noContent().build();
    }

}