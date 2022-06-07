package com.example.lab.controllers;

import com.example.lab.entities.book.request.BookRequestGet;
import com.example.lab.entities.book.request.BookRequestReturn;
import com.example.lab.entities.book.request.misc.RequestState;
import com.example.lab.services.BookRequestGetService;
import com.example.lab.services.BookRequestReturnService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/return_requests")
@CrossOrigin(originPatterns = "*")
@AllArgsConstructor
public class BookRequestReturnController {
    private final BookRequestReturnService requestReturnService;
    private final BookRequestGetService requestService;

    @GetMapping("")
    public ResponseEntity get(@RequestParam(value = "user_id", required = false) String userId) {
        List<BookRequestReturn> requests;

        if (userId == null) {
            requests = requestReturnService.getAll();
        } else {
            requests = requestReturnService.getByUserId(userId);
        }

        return ResponseEntity.ok(requests);
    }

    @PostMapping("")
    public void add(@RequestParam("request_id") long requestId) {
        var request = requestService.getById(requestId);
        var requestReturn = new BookRequestReturn(0, LocalDateTime.now(), request, RequestState.SENT);
        requestReturnService.add(requestReturn);
    }

    @PatchMapping("/{id}")
    public void edit(@PathVariable("id") long id,
                       @RequestParam String state) {
        requestReturnService.edit(id, RequestState.valueOf(state.toUpperCase()));
    }
}
