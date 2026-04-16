package com.example.bootcamp.dto.request;

import com.example.bootcamp.entity.TransactionStatus;
import com.example.bootcamp.entity.User;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.processing.Exclude;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TransactionRequestDTO {

    Double amount;

    User sender;
    User receiver;

    @Enumerated(EnumType.STRING)
    TransactionStatus transactionStatus;
@Builder.Default
    LocalDateTime createdAt=LocalDateTime.now();
}
