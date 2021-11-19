package com.leverx.dealerstat.service;

import com.leverx.dealerstat.dto.UserDTO;
import com.leverx.dealerstat.exception.AlreadyExistsException;
import com.leverx.dealerstat.exception.NotFoundException;
import com.leverx.dealerstat.model.User;

import java.util.List;
import java.util.Optional;

public interface UsersService {
    void save(User user) throws AlreadyExistsException;

    List<User> findAll();

    Optional<UserDTO> findById(Long id);

    void confirm(User user);

    User findByEmail(String email) throws NotFoundException;
}

