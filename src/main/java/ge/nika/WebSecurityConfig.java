package ge.nika;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final OperatorRepository operatorRepository;

    @Autowired
    OperatorUserDetailsService service;

    public WebSecurityConfig(OperatorRepository operatorRepository) {
        this.operatorRepository = operatorRepository;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(service);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/operators").permitAll()
                .and()
                .authorizeRequests()
                .antMatchers("/loanapplications").hasAuthority("ROLE_OPERATOR")
                .anyRequest().authenticated().and().httpBasic();

    }
}
