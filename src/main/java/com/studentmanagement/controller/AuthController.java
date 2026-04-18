package com.studentmanagement.controller;

import com.studentmanagement.model.Account;
import com.studentmanagement.repository.AccountRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthController(AccountRepository accountRepository, PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/register")
    public String showRegisterForm() {
        return "register"; // Trả về file register.html
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("account") Account account, Model model) {
        if (accountRepository.existsByUsername(account.getUsername())) {
            model.addAttribute("error", "Tên đăng nhập đã tồn tại!");
            return "register";
        }

        account.setPassword(passwordEncoder.encode(account.getPassword()));

        if (account.getRole() == null || account.getRole().isEmpty()) {
            account.setRole("ROLE_STUDENT");
        }

        accountRepository.save(account);
        return "redirect:/login?success"; // Đăng ký xong về trang login
    }
}