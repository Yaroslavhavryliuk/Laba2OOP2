package com.example.lab.repositories;

import com.example.lab.entities.book.changeloggers.BalanceLoggerRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BalanceLoggerRecordRepository extends JpaRepository<BalanceLoggerRecord, Long> {
    public List<BalanceLoggerRecord> getAllByBookId(long bookID);

    public List<BalanceLoggerRecord> getAllByDatetimeGreaterThanAndDatetimeLessThan(LocalDateTime periodStart, LocalDateTime periodEnd);
}
