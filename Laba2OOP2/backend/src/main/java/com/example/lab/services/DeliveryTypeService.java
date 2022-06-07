package com.example.lab.services;

import com.example.lab.entities.book.request.misc.DeliveryType;
import com.example.lab.repositories.DeliveryTypeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
public class DeliveryTypeService {
    private final DeliveryTypeRepository repository;

    public List<DeliveryType> getAll() {
        return repository.getAll();
    }

    public DeliveryType get(long id) {
        return repository.getById(id).orElse(null);
    }

    @Transactional
    public void add(DeliveryType deliveryType) {
        repository.save(deliveryType);
    }

    @Transactional
    public void delete(long id) {
        repository.deleteById(id);
    }
}
