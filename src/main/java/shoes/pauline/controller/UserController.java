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
@RequestMapping("/api/users")
@Tag(name = "Users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    @Operation(summary = "Get all userss", description = "Retrieve a list of all available userss.")
    public List<User> getAllUser() {
        return userService.getAllUsers();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get users by ID", description = "Retrieve a specific users by its ID.")
    public ResponseEntity<User> getUsersById(@PathVariable Long id) {
        Optional<User> users = userService.getUserById(id);
        return users.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    @Operation(summary = "Create a new users", description = "Add a new users to the catalog.")
    public User createUsers(@RequestBody User users) {
        return userService.saveUser(users);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a users", description = "Remove a users from the catalog.")
    public ResponseEntity<Void> deleteUsers(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}