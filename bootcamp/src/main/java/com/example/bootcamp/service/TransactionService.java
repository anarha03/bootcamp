package com.example.bootcamp.service;

import com.example.bootcamp.dto.response.TransactionResponseDTO;

import java.util.List;

public interface TransactionService {
    List<TransactionResponseDTO> getMyTransactions(Long userId);
    void paymentToTeacher(Long id, Long teacherId, Double amount);

    void topUp(Long id, Double amount);

    void withdraw(Long id, Double amount);
}
