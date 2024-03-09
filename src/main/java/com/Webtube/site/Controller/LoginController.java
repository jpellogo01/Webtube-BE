    package com.Webtube.site.Controller;

    import com.Webtube.site.Model.User;
    import com.Webtube.site.Repository.UserRepository;
    import com.Webtube.site.Security.Role;
    import io.jsonwebtoken.Claims;
    import io.jsonwebtoken.Jwts;
    import io.jsonwebtoken.SignatureAlgorithm;
    import io.jsonwebtoken.security.Keys;
    import lombok.Getter;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.security.core.authority.AuthorityUtils;
    import org.springframework.security.crypto.password.PasswordEncoder;
    import org.springframework.web.bind.annotation.*;

    import java.time.LocalDate;
    import java.util.Date;

    @CrossOrigin(origins = "http://localhost:3000", allowCredentials = "true")
    @RestController
    @RequestMapping("api/v1")
    public class LoginController {
        private final UserRepository userRepository;
        private final PasswordEncoder passwordEncoder;

        @Autowired
        public LoginController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
            this.userRepository = userRepository;
            this.passwordEncoder = passwordEncoder;
        }

        @PostMapping(path = "/login")
        public ResponseEntity<?> loginUser(@RequestBody User user) {
            // Find user by username from the database
            User storedUser = userRepository.findByUsername(user.getUsername());

            // Check if the user exists and if the provided password matches the hashed password stored in the database
            if (storedUser != null && passwordEncoder.matches(user.getPassword(), storedUser.getPassword())) {
                // If credentials are correct, generate a JWT token
                String token = generateJwtToken(storedUser.getUsername(), storedUser.getRole());

                // Return the token along with user details
                return ResponseEntity.ok(new LoginResponse(token, storedUser));
            } else {
                // If the user doesn't exist or the password doesn't match, return an unauthorized response
                return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
            }
        }

        // Method to generate JWT token
        private String generateJwtToken(String username, Role role) {
            String key = "securesecuresecuresecuresecuresecuresecuresecuresecure";
            Claims claims = Jwts.claims().setSubject(username);
            claims.put("role", role);

            return Jwts.builder()
                    .setClaims(claims)
                    .setIssuedAt(new Date())
                    .setExpiration(java.sql.Date.valueOf(LocalDate.now().plusWeeks(2)))
                    .signWith(SignatureAlgorithm.HS256, key) // Use HS256 algorithm
                    .compact();
        }

        // Response DTO for including token and user details
        @Getter
        static class LoginResponse {
            private final String token;
            private final User user;

            public LoginResponse(String token, User user) {
                this.token = token;
                this.user = user;
            }
        }
    }
