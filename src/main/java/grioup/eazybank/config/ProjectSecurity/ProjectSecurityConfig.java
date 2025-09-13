package grioup.eazybank.config.ProjectSecurity;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.FormLoginDsl;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectSecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        // accept every request
        //http.authorizeHttpRequests((requests) -> ((AuthorizeHttpRequestsConfigurer.AuthorizedUrl) requests.anyRequest()).permitAll());


        // deny every request
        //http.authorizeHttpRequests((requests) -> ((AuthorizeHttpRequestsConfigurer.AuthorizedUrl) requests.anyRequest()).denyAll());


        // authenticate certain requests
       http.authorizeHttpRequests(requests -> requests.requestMatchers(
               "/myAccount","/myBalance","/myLoans","/myCards").authenticated()
               .requestMatchers("/notices","/contact").permitAll()
       );

       http.formLogin(Customizer.withDefaults());
       http.httpBasic(Customizer.withDefaults());
        //http.httpBasic(Customizer.withDefaults());
        // to remove the default login form
//        http.formLogin(flc -> flc.disable());
//        http.httpBasic(hbc -> hbc.disable());
        return http.build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
       UserDetails user= User.withUsername("user").password("password").authorities("read").build();
       UserDetails admin= User.withUsername("admin").password("adminpassword").authorities("admin").build();
         return new InMemoryUserDetailsManager(user,admin);


    }
}
