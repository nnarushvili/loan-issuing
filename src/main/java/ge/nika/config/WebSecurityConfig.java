package ge.nika.config;

import ge.nika.operator.OperatorUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    OperatorUserDetailsService service;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(service);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.headers().frameOptions().sameOrigin().and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/operators").permitAll()
                .and()
                .authorizeRequests()
                .antMatchers("/loanapplications/client").permitAll()
                .and()
                .authorizeRequests()
                .antMatchers("/loanapplications").hasAuthority("ROLE_OPERATOR")
                .anyRequest().authenticated().and().httpBasic();

    }
}
