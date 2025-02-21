package shoes.pauline.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import shoes.pauline.entity.*;
import shoes.pauline.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    // Récupérer un utilisateur par son pseudo ou son email
    public Optional<User> findByUsernameOrEmail(String identifier) {
        Optional<User> user = userRepository.findByUsername(identifier);
        if (user.isEmpty()) {
            user = userRepository.findByEmail(identifier);
        }
        return user;
    }
}