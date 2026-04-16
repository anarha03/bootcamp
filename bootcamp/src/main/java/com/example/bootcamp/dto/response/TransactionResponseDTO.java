package com.example.bootcamp.dto.response;

import com.example.bootcamp.entity.TransactionStatus;
import com.example.bootcamp.entity.User;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TransactionResponseDTO {

    Long id;

    Double amount;

    String  sender;
    String receiver;

    @Enumerated(EnumType.STRING)
    TransactionStatus transactionStatus;

    String description;

    LocalDateTime createdAt;

}
