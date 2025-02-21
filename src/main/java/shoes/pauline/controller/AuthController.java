package shoes.pauline.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import shoes.pauline.entity.User;
import shoes.pauline.security.JwtUtil;
import shoes.pauline.service.UserService;

import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JwtUtil jwtUtil;

    // Authentification avec pseudo ou email + mot de passe
    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@RequestParam String identifier, @RequestParam String password) {
        // Vérifier si l'utilisateur existe par pseudo ou email
        Optional<User> user = userService.findByUsernameOrEmail(identifier);
        if (user.isPresent()) {
            User foundUser = user.get();
            // Vérification du mot de passe
            if (foundUser.getPassword().equals(password)) {
                // Génération du token JWT
                String token = jwtUtil.generateToken(foundUser.getUsername());
                return ResponseEntity.ok().body("Bearer " + token);
            } else {
                return ResponseEntity.status(401).body("Mot de passe incorrect");
            }
        } else {
            return ResponseEntity.status(404).body("Utilisateur non trouvé");
        }
    }
}
