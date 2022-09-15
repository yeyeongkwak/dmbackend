package com.spring.security;

import com.spring.security.exception.JwtAccessDeniedHandler;
import com.spring.security.exception.JwtAuthenticationEntryPoint;

import lombok.RequiredArgsConstructor;

import javax.servlet.http.HttpSession;

import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;
import org.springframework.boot.autoconfigure.security.ConditionalOnDefaultWebSecurity;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.WebSecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;

@EnableWebSecurity
@RequiredArgsConstructor
@Configuration(proxyBeanMethods = false)
@ConditionalOnDefaultWebSecurity
@ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
public class SecurityConfig {
	
    private final JwtAuthTokenProvider jwtAuthTokenProvider;
    private final JwtAuthenticationEntryPoint authenticationErrorHandler;
    private final JwtAccessDeniedHandler jwtAccessDeniedHandler;

    @Bean
    public Pbkdf2PasswordEncoder passwordEncorder() {
    	return new Pbkdf2PasswordEncoder();
    }
    
    
    @Bean
    @Order(SecurityProperties.BASIC_AUTH_ORDER)
    protected SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .cors(Customizer.withDefaults())
                
//                .cors().configurationSource(corsConfigurationSource())
//                .and()

                .exceptionHandling()
                .authenticationEntryPoint(authenticationErrorHandler)
                .accessDeniedHandler(jwtAccessDeniedHandler)

                .and()
                .headers()
                .frameOptions()	
                .sameOrigin()

                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                

                .and()
                .authorizeRequests() // 인증절차에 대한 진행
//                .antMatchers("/**").permitAll()
                .antMatchers("/api/mail/**").permitAll()
                .antMatchers("/api/signup").permitAll()
                .antMatchers("/api/login").permitAll()
                .antMatchers("/api/findidpw").permitAll()
                .antMatchers("/api/mail/checkmail").permitAll()
                .antMatchers("/api/checkuser").permitAll()
                .antMatchers("/main").authenticated()
                .antMatchers("/api/logout").authenticated()
                .antMatchers("/ws-dm/**").permitAll()
                .anyRequest().authenticated()
                                
//                .and()
//                .logout()
//                .logoutUrl("/api/logout")
//                .logoutSuccessUrl("/")
//                .deleteCookies("accessToken")
                
                .and()
                .apply(securityConfigurerAdapter());
        
        
        return http.build();
    }

    private JwtConfigurer securityConfigurerAdapter() {
        return new JwtConfigurer(jwtAuthTokenProvider);
    }
}
