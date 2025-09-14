package grioup.eazybank.config.ProjectSecurity;

import grioup.eazybank.Repositories.CustomerRepository;
import grioup.eazybank.model.Customer;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EazyBankUserDetailService implements UserDetailsService {
    
    private final CustomerRepository customerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Customer customer=  customerRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with email: " + username));

        List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
        return new User(customer.getEmail(),customer.getPwd(),grantedAuthorities);
    }
}
