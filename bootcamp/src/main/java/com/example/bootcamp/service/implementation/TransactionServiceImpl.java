package com.example.bootcamp.service.implementation;

import com.example.bootcamp.dto.response.TransactionResponseDTO;
import com.example.bootcamp.exception.ResponseCode;
import com.example.bootcamp.exception.types.HaveNotEnoughBalanceException;
import com.example.bootcamp.exception.types.StudentNotFoundException;
import com.example.bootcamp.exception.types.TeacherNotFoundException;
import com.example.bootcamp.exception.types.UserNotFoundException;
import com.example.bootcamp.model.entity.Student;
import com.example.bootcamp.model.entity.Teacher;
import com.example.bootcamp.model.entity.WalletTransaction;
import com.example.bootcamp.model.entity.User;
import com.example.bootcamp.model.enums.TransactionStatus;
import com.example.bootcamp.repository.StudentRepository;
import com.example.bootcamp.repository.TeacherRepository;
import com.example.bootcamp.repository.TransactionRepository;
import com.example.bootcamp.repository.UserRepository;
import com.example.bootcamp.service.TransactionService;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

import static com.example.bootcamp.model.enums.TransactionStatus.INCOME;
import static com.example.bootcamp.model.enums.TransactionStatus.OUTCOME;

@Service
public class TransactionServiceImpl implements TransactionService {
    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;
    private final StudentRepository studentRepository;
    private final TeacherRepository teacherRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository, UserRepository userRepository, StudentRepository studentRepository, TeacherRepository teacherRepository) {
        this.transactionRepository = transactionRepository;
        this.userRepository = userRepository;
        this.studentRepository = studentRepository;
        this.teacherRepository = teacherRepository;
    }

    @Override
    public List<TransactionResponseDTO> getMyTransactions(Long userId) {
        List<WalletTransaction> walletTransactions = transactionRepository.findBySenderIdOrReceiverIdOrderByCreatedAtDesc(userId, userId);
        return walletTransactions.stream()
                .map(t -> toResponse(t, userId))
                .toList();
    }

    @Override
    @Transactional
    public void paymentToTeacher(Long studentUserId, Long teacherId, Double amount) {
        Student student = getStudentById(studentUserId);
        if (student.getUser().getBalance() < amount) {
            throw new HaveNotEnoughBalanceException(ResponseCode.BALANCE_IS_NOT_ENOUGH, student.getUser().getBalance());
        }
        Teacher teacher = getTeacherById(teacherId);
        teacher.getUser().setBalance(teacher.getUser().getBalance() + amount);
        student.getUser().setBalance(student.getUser().getBalance() - amount);
        transactionRepository.save(WalletTransaction.builder()
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
        User user = getUserById(id);
        user.setBalance(user.getBalance() + amount);
        WalletTransaction topUp = WalletTransaction.builder()
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
        User user = getUserById(id);
        if (user.getBalance() < amount) {
            throw new HaveNotEnoughBalanceException(ResponseCode.BALANCE_IS_NOT_ENOUGH, user.getBalance());
        }
        user.setBalance(user.getBalance() - amount);
        WalletTransaction withdraw = WalletTransaction.builder()
                .amount(amount)
                .sender(user)
                .description("Withdraw")
                .transactionStatus(OUTCOME)
                .createdAt(LocalDateTime.now())
                .build();
        transactionRepository.save(withdraw);
        userRepository.save(user);
    }

    private TransactionResponseDTO toResponse(WalletTransaction t, Long currentUserId) {

        boolean isIncome = t.getReceiver() != null
                && t.getReceiver().getId().equals(currentUserId);

        TransactionStatus status = isIncome ? INCOME : OUTCOME;

        String receiver = !isIncome
                ? (t.getSender() != null ? t.getSender().getEmail() : "Sistem")
                : (t.getReceiver() != null ? t.getReceiver().getEmail() : "Sistem");
        String sender = isIncome
                ? (t.getSender() != null ? t.getSender().getEmail() : "Sistem")
                : (t.getReceiver() != null ? t.getReceiver().getEmail() : "Sistem");

        return TransactionResponseDTO.builder()
                .id(t.getId())
                .amount(t.getAmount())
                .transactionStatus(status)
                .receiver(receiver)
                .sender(sender)
                .createdAt(t.getCreatedAt())
                .build();

    }

    private Student getStudentById(Long id) {
        return studentRepository.findById(id).orElseThrow(() -> new StudentNotFoundException(ResponseCode.STUDENT_NOT_FOUND, id, HttpStatus.NOT_FOUND));
    }

    private Teacher getTeacherById(Long id) {
        return teacherRepository.findById(id).orElseThrow(() -> new TeacherNotFoundException(ResponseCode.TEACHER_NOT_FOUND, id, HttpStatus.NOT_FOUND));
    }

    private User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(ResponseCode.USER_IS_NOT_FOUND, id, HttpStatus.NOT_FOUND));
    }
}
