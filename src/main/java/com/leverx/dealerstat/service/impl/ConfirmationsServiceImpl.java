package com.leverx.dealerstat.service.impl;

import com.leverx.dealerstat.model.Confirmation;
import com.leverx.dealerstat.model.User;
import com.leverx.dealerstat.service.ConfirmationsService;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.UUID;

@Service
public class ConfirmationsServiceImpl implements ConfirmationsService {
    @Override
    public void save(User userEntity) {
        String code = UUID.randomUUID().toString();
        Date expirationTime = new Date(24 * 60 * 60 * 1000);

    }
}
