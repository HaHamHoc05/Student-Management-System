package com.studentmanagement.controller;

import com.studentmanagement.model.Account;
import com.studentmanagement.repository.AccountRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder; // Đã được cấu hình là @Bean trong SecurityConfig

    public AuthController(AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public String register(@RequestBody Account account) {
        // Kiểm tra xem username đã tồn tại chưa
        if (accountRepository.existsByUsername(account.getUsername())) {
            return "Lỗi: Tên đăng nhập đã tồn tại!";
        }

        // Mã hóa mật khẩu trước khi lưu xuống database
        account.setPassword(passwordEncoder.encode(account.getPassword()));

        // Nếu client không gửi lên role, mặc định cấp quyền sinh viên
        if (account.getRole() == null || account.getRole().isEmpty()) {
            account.setRole("ROLE_STUDENT");
        }

        accountRepository.save(account);
        return "Đăng ký thành công!";
    }
}