package com.example.lab.controllers;

import com.example.lab.entities.book.changeloggers.BalanceLoggerRecord;
import com.example.lab.services.BalanceLoggerRecordService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/balance_log")
@CrossOrigin(originPatterns = "*")
@AllArgsConstructor
public class BalanceLoggerRecordController {
    private final BalanceLoggerRecordService balanceLoggerService;

    @GetMapping("")
    public ResponseEntity get(@RequestParam(value = "book_id", required = false) Long bookId,
                              @RequestParam(value = "from", required = false) String periodStart,
                              @RequestParam(value = "to", required = false) String periodEnd) {
        List<BalanceLoggerRecord> logger;

        if (bookId != null) {
            logger = balanceLoggerService.getByBookId(bookId);
        } else if (periodStart != null && periodEnd != null) {
            logger = balanceLoggerService.getInPeriod(LocalDateTime.parse(periodStart), LocalDateTime.parse(periodEnd));
        } else {
            logger = balanceLoggerService.getAll();
        }

        return ResponseEntity.ok(logger);
    }
}
