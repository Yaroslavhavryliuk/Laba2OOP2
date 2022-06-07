package com.example.lab.services;

import com.example.lab.entities.book.changeloggers.BalanceLoggerRecord;
import com.example.lab.repositories.BalanceLoggerRecordRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class BalanceLoggerRecordService {
    private final BalanceLoggerRecordRepository repository;

    public List<BalanceLoggerRecord> getAll() {
        return repository.getAll();
    }

    public List<BalanceLoggerRecord> getByBookId(long bookId) {
        return repository.getAllByBookId(bookId);
    }

    public List<BalanceLoggerRecord> getInPeriod(LocalDateTime periodStart, LocalDateTime periodEnd) {
        return repository.getAllByDatetimeGreaterThanAndDatetimeLessThan(periodStart, periodEnd);
    }
}
