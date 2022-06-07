package com.example.lab.services;

import com.example.lab.entities.book.changeloggers.BalanceLoggerRecord;
import com.example.lab.entities.book.request.BookRequestGet;
import com.example.lab.entities.book.request.misc.RequestState;
import com.example.lab.repositories.BalanceLoggerRecordRepository;
import com.example.lab.repositories.BookRequestGetRepository;
import com.example.lab.repositories.BookStatsRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class BookRequestGetService {
    private final BookRequestGetRepository requestGetRepository;
    private final BalanceLoggerRecordRepository balanceLoggerRepository;
    private final BookStatsRepository statsRepository;

    public List<BookRequestGet> getAll() {
        return requestGetRepository.getAll();
    }

    public BookRequestGet getById(long id) {
        return requestGetRepository.getById(id);
    }

    public List<BookRequestGet> getByUserId(String userId) {
        return requestGetRepository.getAllByUserId(userId);
    }

    @Transactional
    public void add(BookRequestGet request) {
        requestGetRepository.save(request);
    }

    @Transactional
    public void edit(long id, RequestState state) {
        var request = requestGetRepository.getById(id);
        request.setState(state);
        requestGetRepository.save(request);

        if (state == RequestState.PROCESSED) {
            balanceLoggerRepository.save(new BalanceLoggerRecord(0, LocalDateTime.now(), request.getBook(), -1, "requested"));

            var stats = statsRepository.getByBookId(request.getBook().getId());
            stats.setTotalRequests(stats.getTotalRequests() + 1);
            statsRepository.save(stats);
        }
    }
}
