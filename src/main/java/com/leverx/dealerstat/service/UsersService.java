package com.leverx.dealerstat.service;

import com.leverx.dealerstat.dto.UserDTO;
import com.leverx.dealerstat.exception.AlreadyExistsException;
import com.leverx.dealerstat.exception.NotFoundException;
import com.leverx.dealerstat.model.Role;
import com.leverx.dealerstat.model.User;
import org.springframework.http.ResponseEntity;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public interface UsersService {
    void save(User user) throws AlreadyExistsException;

    List<UserDTO> findAll();

    Optional<UserDTO> findById(Long id);

    void confirm(User user);
}

