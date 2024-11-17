package com.sparta.gaeppa.security.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sparta.gaeppa.members.service.MemberService;
import com.sparta.gaeppa.security.authentication.filters.LoginFilter;
import com.sparta.gaeppa.security.jwts.filters.JWTFilterV1;
import com.sparta.gaeppa.security.jwts.filters.JWTFilterV2;
import com.sparta.gaeppa.security.jwts.service.RefreshService;
import com.sparta.gaeppa.security.jwts.utils.JwtUtil;
import com.sparta.gaeppa.security.jwts.utils.WebCookieUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {

    private final CustomAuthenticationEntryPointConfig authenticationEntryPoint;
    private final ObjectMapper objectMapper;
    private final MemberService memberService;
    private final JwtUtil jwtUtil;
    private final RefreshService refreshService;
    private final AuthenticationConfiguration authenticationConfiguration;
    private final WebCookieUtil webCookieUtil;

    @Bean
    public JWTFilterV2 jwtFilter() {
        return new JWTFilterV2(jwtUtil, webCookieUtil);
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public LoginFilter loginFilter() throws Exception {
        LoginFilter loginFilter = new LoginFilter(authenticationConfiguration.getAuthenticationManager(), objectMapper, memberService, jwtUtil, refreshService);
        loginFilter.setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/v1/members/login", "POST"));
        return loginFilter;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                .csrf(csrf -> csrf.disable())
                .formLogin(form -> form.disable())
                .httpBasic(httpBasic -> httpBasic.disable())
                .logout(logout -> logout.disable())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))

                // 인가 설정
//                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/v1/members/login", "/api/v1/members/join", "/api/v1/members/master/join", "/error").permitAll()
//                        .requestMatchers("/api/v1/admin/**").hasRole("ADMIN")
//                        .requestMatchers("/api/v1/profiles/**").authenticated()
//                        // ORDER
//                        .requestMatchers("/api/v1/orders/**").authenticated()
//                        .requestMatchers("/api/v1/payments/**").authenticated()
//                        .requestMatchers("/api/v1/reviews/**").authenticated()
//                        // PRODUCT
//                        .requestMatchers("/api/v1/products/**").authenticated()
//                        .anyRequest().denyAll()
                        .anyRequest().permitAll()
                )// 나머지 요청은 모두 차단
                .exceptionHandling(exception -> exception.authenticationEntryPoint(authenticationEntryPoint));

        // 필터 순서: LoginFilter -> JWTFilter
        http.addFilterBefore(loginFilter(), UsernamePasswordAuthenticationFilter.class);
        http.addFilterAfter(jwtFilter(), LoginFilter.class);


        return http.build();
    }

}
