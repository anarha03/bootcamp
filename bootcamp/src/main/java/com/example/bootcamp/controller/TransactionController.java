package com.example.bootcamp.controller;

import com.example.bootcamp.dto.response.TransactionResponseDTO;
import com.example.bootcamp.entity.security.CustomUserDetails;
import com.example.bootcamp.service.TransactionService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public List<TransactionResponseDTO>getMyTransactions(@AuthenticationPrincipal CustomUserDetails userDetails){
        return transactionService.getMyTransactions(userDetails.getId());
    }
    @PostMapping("/payment/{teacherId}")
    public void payment(@AuthenticationPrincipal CustomUserDetails userDetails,
                        @PathVariable Long teacherId,
                        @RequestParam Double amount){
        transactionService.paymentToTeacher(userDetails.getId(),teacherId,amount);
    }
    @PostMapping("/topUp")
    public void topUp(@AuthenticationPrincipal CustomUserDetails userDetails,
                      @RequestParam Double amount){
        transactionService.topUp(userDetails.getId(),amount);
    }
    @PostMapping("/withdraw")
    public void withdraw(@AuthenticationPrincipal CustomUserDetails userDetails,
                         @RequestParam Double amount){
        transactionService.withdraw(userDetails.getId(),amount);
    }
}
