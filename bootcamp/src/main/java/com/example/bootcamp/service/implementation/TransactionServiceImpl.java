package com.example.bootcamp.service.implementation;

import com.example.bootcamp.dto.response.TransactionResponseDTO;
import com.example.bootcamp.entity.*;
import com.example.bootcamp.repository.StudentRepository;
import com.example.bootcamp.repository.TeacherRepository;
import com.example.bootcamp.repository.TransactionRepository;
import com.example.bootcamp.repository.UserRepository;
import com.example.bootcamp.service.TransactionService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.example.bootcamp.entity.TransactionStatus.INCOME;
import static com.example.bootcamp.entity.TransactionStatus.OUTCOME;

@Service
public class TransactionServiceImpl implements TransactionService {
    public final TransactionRepository transactionRepository;
    public final UserRepository userRepository;
    public final StudentRepository studentRepository;
    public final TeacherRepository teacherRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository, UserRepository userRepository, StudentRepository studentRepository, TeacherRepository teacherRepository) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
    }

    @Override
    public List<TransactionResponseDTO> getMyTransactions(Long userId) {
        List<Transaction> transactions = transactionRepository.findBySenderIdOrReceiverIdOrderByCreatedAtDesc(userId, userId);
        return transactions.stream()
                .map(t -> toResponse(t, userId))
                .toList();
    }

    @Override
    @Transactional
    public void paymentToTeacher(Long id, Long teacherId, Double amount) {
        Student student = studentRepository.findByUserId(id).orElseThrow(RuntimeException::new);
        if (student.getUser().getBalance() < amount) {
            throw new RuntimeException("Balansinizda kifayet qeder vesait yoxdur!");
        }
        Teacher teacher = teacherRepository.findById(teacherId).orElseThrow(RuntimeException::new);
        teacher.getUser().setBalance(teacher.getUser().getBalance() + amount);
        student.getUser().setBalance(student.getUser().getBalance() - amount);
        transactionRepository.save(Transaction.builder()
                .receiver(teacher.getUser())
                .sender(student.getUser())
                .createdAt(LocalDateTime.now())
                .amount(amount)
                .build());
        teacherRepository.save(teacher);
        studentRepository.save(student);

    }

    @Override
    @Transactional
    public void topUp(Long id, Double amount) {
        User user = userRepository.findById(id).orElseThrow(RuntimeException::new);
        user.setBalance(user.getBalance()+amount);
        Transaction topUp = Transaction.builder()
                .amount(amount)
                .receiver(user)
                .description("TopUp")
                .transactionStatus(INCOME)
                .createdAt(LocalDateTime.now())
                .build();
        transactionRepository.save(topUp);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void withdraw(Long id, Double amount) {
        User user = userRepository.findById(id).orElseThrow(RuntimeException::new);
        if (user.getBalance()<amount){
            throw new RuntimeException();
        }
        user.setBalance(user.getBalance()-amount);
        Transaction withdraw = Transaction.builder()
                .amount(amount)
                .sender(user)
                .description("Withdraw")
                .transactionStatus(OUTCOME)
                .createdAt(LocalDateTime.now())
                .build();
        transactionRepository.save(withdraw);
        userRepository.save(user);
    }

    private TransactionResponseDTO toResponse(Transaction t, Long currentUserId) {

        boolean isIncome = t.getReceiver() != null
                && t.getReceiver().getId().equals(currentUserId);

        TransactionStatus status = isIncome ? INCOME : OUTCOME;

        String description = isIncome
                ? (t.getSender() != null ? t.getSender().getEmail() : "Sistem")
                : (t.getReceiver() != null ? t.getReceiver().getEmail() : "Sistem");

        return TransactionResponseDTO.builder()
                .id(t.getId())
                .amount(t.getAmount())
                .transactionStatus(status)
                .description(description)
                .createdAt(t.getCreatedAt())
                .build();

    }
}
