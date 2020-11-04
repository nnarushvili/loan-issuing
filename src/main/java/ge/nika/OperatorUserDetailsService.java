package ge.nika;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class OperatorUserDetailsService implements UserDetailsService {

    @Autowired
    private OperatorRepository repository;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Optional<Operator> optional = repository.findByUsername(s);
        if (optional.isPresent()) {
            BCryptPasswordEncoder encoder = passwordEncoder();
            Operator operator = optional.get();
            User user = new User(operator.getUsername(), encoder.encode(operator.getPassword()), operator.getAuthorities());
            return user;
        }
        throw new UsernameNotFoundException(s);

    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
