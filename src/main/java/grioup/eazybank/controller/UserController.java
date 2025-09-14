package grioup.eazybank.controller;


import grioup.eazybank.Repositories.CustomerRepository;
import grioup.eazybank.model.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    
    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;
    
    @GetMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody Customer customer) {
        try {
            customerRepository.findByEmail(customer.getEmail())
                    .ifPresent(c -> {
                        throw new RuntimeException("User already exists.");
                    });
            String hashedPassword = passwordEncoder.encode(customer.getPwd());
            customer.setPwd(hashedPassword);
            customerRepository.save(customer);
            return ResponseEntity.status(HttpStatus.CREATED)
                   .body("User registered successfully.");
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while registering the user."+e.getMessage());
        }
        
    }
}
