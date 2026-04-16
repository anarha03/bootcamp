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
    public final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public List<TransactionResponseDTO>getMyTransactions(@AuthenticationPrincipal CustomUserDetails userDetails){
        Long id = userDetails.getId();
        return transactionService.getMyTransactions(id);
    }
    @PostMapping("/payment/{id}")
    public void payment(@AuthenticationPrincipal CustomUserDetails userDetails,
                        @PathVariable Long id,
                        @RequestParam Double amount){
        transactionService.paymentToTeacher(userDetails.getId(),id,amount);
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
