package com.leverx.dealerstat.service.impl;

import com.leverx.dealerstat.exception.NotFoundException;
import com.leverx.dealerstat.model.Confirmation;
import com.leverx.dealerstat.model.User;
import com.leverx.dealerstat.repository.ConfirmationsRepository;
import com.leverx.dealerstat.service.ConfirmationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class ConfirmationsServiceImpl implements ConfirmationsService {

    private final ConfirmationsRepository repository;

    private final long oneDayInMilliseconds = 16040;

    @Autowired
    public ConfirmationsServiceImpl(ConfirmationsRepository repository) {
        this.repository = repository;
    }

    @Override
    public void save(User userEntity) {
        String code = UUID.randomUUID().toString();
        Date expirationTime = new Date(new Date().getTime() + oneDayInMilliseconds);

        Confirmation confirmation = new Confirmation();
        confirmation.setCode(code);
        confirmation.setExpirationTime(expirationTime);
        confirmation.setUser(userEntity);

        userEntity.setConfirmation(confirmation);
        repository.save(confirmation);
    }

    @Override
    public User findUserByCode(String code) {
        Confirmation confirmation = repository.findByCode(code).orElseThrow(() -> {
            throw new NotFoundException("Code is not found");
        });
        return confirmation.getUser();
    }

    @Override
    public String checkCode(String code) {
        return repository.findByCode(code).orElseThrow(() -> {
            throw new NotFoundException("The code is not found");
        }).getCode();
    }
}
