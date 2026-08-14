package com.example.bootcamp.dto.request;

import com.example.bootcamp.model.enums.TransactionStatus;
import com.example.bootcamp.model.entity.User;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class TransactionRequestDTO {
    @NotBlank
    Double amount;

    User sender;
    User receiver;

    @Enumerated(EnumType.STRING)
    TransactionStatus transactionStatus;
@Builder.Default
    LocalDateTime createdAt=LocalDateTime.now();
}
