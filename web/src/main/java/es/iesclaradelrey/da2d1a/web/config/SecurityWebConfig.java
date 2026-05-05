package es.iesclaradelrey.da2d1a.web.config;

import es.iesclaradelrey.da2d1a.security.handlers.CustomAuthFailureHandler;
import es.iesclaradelrey.da2d1a.security.handlers.CustomAuthSuccessHandler;
import es.iesclaradelrey.da2d1a.security.handlers.CustomLogoutSuccessHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityWebConfig {

    @Autowired
    private CustomAuthSuccessHandler authSuccessHandler;

    @Autowired
    private CustomAuthFailureHandler authFailureHandler;

    @Autowired
    private CustomLogoutSuccessHandler logoutSuccessHandler;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/h2-console/**").authenticated()
                .requestMatchers("/admin/**").hasRole("ADMIN")
                .requestMatchers("/users/profile/**").authenticated()
                .requestMatchers("/register").anonymous()
                .anyRequest().permitAll()
            )
            .csrf(csrf -> csrf
                .ignoringRequestMatchers("/h2-console/**")
            )
            .headers(headers -> headers
                .frameOptions(fo -> fo.sameOrigin())
            )
            .formLogin(fl -> fl
                .loginPage("/login")
                .permitAll()
                .successHandler(authSuccessHandler)
                .failureHandler(authFailureHandler)
            )
            .httpBasic(hb -> hb.disable())
            .logout(logout -> logout
                .logoutSuccessHandler(logoutSuccessHandler)
            );

        return http.build();
    }
}
