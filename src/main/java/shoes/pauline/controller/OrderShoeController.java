package shoes.pauline.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shoes.pauline.entity.*;
import shoes.pauline.service.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/orderShoes")
@Tag(name = "OrderShoe")
public class OrderShoeController {

    @Autowired
    private OrderShoeService orderShoeService;

    @GetMapping
    @Operation(summary = "Get all orderShoes", description = "Retrieve a list of all available orderShoes.")
    public List<OrderShoe> getAllOrderShoes() {
        return orderShoeService.getAllOrderShoes();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get orderShoe by ID", description = "Retrieve a specific orderShoe by its ID.")
    public ResponseEntity<OrderShoe> getOrderShoeById(@PathVariable Long id) {
        Optional<OrderShoe> orderShoe = orderShoeService.getOrderShoeById(id);
        return orderShoe.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Create a new orderShoe", description = "Add a new orderShoe to the catalog.")
    public OrderShoe createOrderShoe(@RequestBody OrderShoe orderShoe) {
        return orderShoeService.saveOrderShoe(orderShoe);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a orderShoe", description = "Remove a orderShoe from the catalog.")
    public ResponseEntity<Void> deleteOrderShoe(@PathVariable Long id) {
        orderShoeService.deleteOrderShoe(id);
        return ResponseEntity.noContent().build();
    }
}
