package com.example.lab.services;

import com.example.lab.entities.book.BookStats;
import com.example.lab.entities.book.changeloggers.BalanceLoggerRecord;
import com.example.lab.repositories.BalanceLoggerRecordRepository;
import com.example.lab.repositories.BookRepository;
import com.example.lab.repositories.BookStatsRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class BookStatsService {
    private final BookStatsRepository statsRepository;
    private final BookRepository bookRepository;
    private final BalanceLoggerRecordRepository balanceLoggerRepository;

    public List<BookStats> getAll() {
        return statsRepository.getAll();
    }

    public BookStats getByBookId(long bookId) {
        return statsRepository.getByBookId(bookId);
    }

    @Transactional
    public void edit(long bookId, int newAmount, String comment) {
        var stats = statsRepository.getByBookId(bookId);
        var book = bookRepository.getById(bookId).orElse(null);
        var diff = newAmount - stats.getAmount();

        stats.setAmount(newAmount);
        statsRepository.save(stats);
        balanceLoggerRepository.save(new BalanceLoggerRecord(0, LocalDateTime.now(), book, diff, comment));
    }

}
