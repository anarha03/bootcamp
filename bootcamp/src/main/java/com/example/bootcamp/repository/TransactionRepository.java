package com.example.bootcamp.repository;

import com.example.bootcamp.model.entity.WalletTransaction;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<WalletTransaction, Long> {

    @EntityGraph(attributePaths = {"sender", "receiver"})
    List<WalletTransaction> findBySenderIdOrReceiverIdOrderByCreatedAtDesc(Long sender_id, Long receiver_id);
}
