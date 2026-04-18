package com.studentmanagement.controller;

import com.studentmanagement.model.Account;
import com.studentmanagement.repository.AccountRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/accounts")
public class AdminAccountController {

    private final AccountRepository accountRepository;
    private final PasswordEncoder passwordEncoder;

    public AdminAccountController(AccountRepository accountRepository,
                                  PasswordEncoder passwordEncoder) {
        this.accountRepository = accountRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Xem danh sách tài khoản
    @GetMapping
    public String listAccounts(Model model) {
        model.addAttribute("accounts", accountRepository.findAll());
        return "admin/account-list";
    }

    // Đổi quyền (role) của tài khoản
    @PostMapping("/change-role/{id}")
    public String changeRole(@PathVariable Long id,
                             @RequestParam String role) {
        Account acc = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy tài khoản"));
        acc.setRole(role);
        accountRepository.save(acc);
        return "redirect:/admin/accounts";
    }

    @PostMapping("/reset-password/{id}")
    public String resetPassword(@PathVariable Long id) {
        Account acc = accountRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy tài khoản"));
        acc.setPassword(passwordEncoder.encode("123456"));
        accountRepository.save(acc);
        return "redirect:/admin/accounts?reset=true";
    }
    @GetMapping("/delete/{id}")
    public String deleteAccount(@PathVariable Long id) {
        accountRepository.deleteById(id);
        return "redirect:/admin/accounts";
    }
}