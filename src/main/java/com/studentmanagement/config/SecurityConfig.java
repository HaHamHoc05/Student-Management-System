package com.studentmanagement.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(); // Dùng để mã hóa mật khẩu trong Database
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable()) // Tạm tắt CSRF để dễ test API qua Postman
                .authorizeHttpRequests(auth -> auth
                        // Cấp quyền truy cập tự do cho các file tĩnh hoặc trang đăng ký
                        .requestMatchers("/css/**", "/js/**", "/auth/**").permitAll()

                        // Phân quyền cụ thể
                        .requestMatchers("/classes/**", "/teachers/**").hasRole("ADMIN")
                        .requestMatchers("/grades/**").hasAnyRole("ADMIN", "TEACHER")

                        // Thêm 3 dòng này vào phần phân quyền
                        .requestMatchers("/admin/**").hasRole("ADMIN")
                        .requestMatchers("/teacher/**").hasRole("TEACHER")
                        .requestMatchers("/student/**").hasRole("STUDENT")
                        // Các request còn lại bắt buộc phải đăng nhập
                        .anyRequest().authenticated()

                )
                .formLogin(form -> form
                        .loginPage("/login") // Báo cho Spring biết đường dẫn tới trang đăng nhập của bạn
                        .defaultSuccessUrl("/dashboard", true) // Thành công thì vào trang dashboard
                        .permitAll()

                )
                .logout(logout -> logout
                        .logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout") // Đăng xuất xong quay về login
                        .permitAll()
                );

        return http.build();
    }
}