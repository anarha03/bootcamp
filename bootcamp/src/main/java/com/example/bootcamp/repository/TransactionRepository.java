package com.example.bootcamp.repository;

import com.example.bootcamp.entity.Transaction;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    @EntityGraph(attributePaths = {"sender", "receiver"})
    List<Transaction> findBySenderIdOrReceiverIdOrderByCreatedAtDesc(Long sender_id, Long receiver_id);
}
