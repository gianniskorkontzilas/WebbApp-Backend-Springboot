package Knowledge.WebApp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable()) // Απενεργοποίηση CSRF για την ανάπτυξη (μόνο για δοκιμές)
//                .authorizeHttpRequests(authorize -> authorize
//                        .requestMatchers("/api/stores", "/api/customers").authenticated() // Προστατευμένα endpoints
//                        .anyRequest().permitAll() // Όλα τα υπόλοιπα επιτρέπονται
                .authorizeHttpRequests(authorize -> authorize
//                        .requestMatchers("/api/stores", "/api/customers").permitAll() // Δημόσια πρόσβαση
                                .anyRequest().permitAll()
                );
//                        .anyRequest().authenticated() // Όλα τα υπόλοιπα απαιτούν αυθεντικοποίηση




//                .httpBasic(withDefaults()); // Ενεργοποίηση Basic Authentication

        return http.build();
    }

//    @Bean
//    public UserDetailsService userDetailsService(PasswordEncoder passwordEncoder) {
//        UserDetails user = User.withUsername("user")
//                .password(passwordEncoder.encode("yourpassword"))
//                .roles("USER")
//                .build();
//
//        return new InMemoryUserDetailsManager(user);
//    }

    @Bean
    public UserDetailsService userDetailsService() {
        UserDetails user = User.withUsername("user")
                .password("{noop}yourpassword")  // Χρησιμοποιούμε {noop} για να δείξουμε ότι δεν χρησιμοποιούμε κρυπτογράφηση
                .roles("USER")
                .build();

        return new InMemoryUserDetailsManager(user);
    }


//    @Bean
//    public PasswordEncoder passwordEncoder() {
//        return new BCryptPasswordEncoder(); // Κρυπτογράφηση κωδικών με BCrypt
//    }
}

