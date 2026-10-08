package tw.brad.spring6.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity){
        /*
            1. 順序比對, 比對到就決定
                => .anyRequest()
            2. hasRole("ADMIN") => ROLE_ADMIN
         */
        httpSecurity
            .authorizeHttpRequests(auth ->
                    auth.requestMatchers("/login", "/js/**", "/css/**", "/test/**").permitAll()
                            .requestMatchers("/members/**").hasAnyRole("ADMIN","USER")
                            .requestMatchers("/admin/**").hasRole("ADMIN")
                            .anyRequest().authenticated()
            ).formLogin(form -> form.loginPage("/login")
                        .usernameParameter("account")
                        .passwordParameter("passwd")
                        .loginProcessingUrl("/doLogin")
                        .defaultSuccessUrl("/main")
                        .failureForwardUrl("/login?error")
                        .permitAll())
                .logout(logout -> logout.logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .invalidateHttpSession(true)
                        .deleteCookies("JSESSIONID"))
                .exceptionHandling(
                        e -> e.accessDeniedPage("/page403"));

        return httpSecurity.build();
    }

    @Bean
    public PasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(
            AuthenticationConfiguration config) throws Exception{
        return config.getAuthenticationManager();
    }

}
