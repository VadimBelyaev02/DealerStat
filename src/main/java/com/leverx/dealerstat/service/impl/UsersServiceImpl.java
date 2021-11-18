package com.leverx.dealerstat.service.impl;

import com.leverx.dealerstat.dto.UserDTO;
import com.leverx.dealerstat.exception.AlreadyExistsException;
import com.leverx.dealerstat.model.Role;
import com.leverx.dealerstat.model.User;
import com.leverx.dealerstat.repository.ConfirmationsRepository;
import com.leverx.dealerstat.repository.UsersRepository;
import com.leverx.dealerstat.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UsersServiceImpl implements UsersService {

    private final UsersRepository repository;
    private final ConfirmationsRepository confirmationsRepository;

    @Autowired
    public UsersServiceImpl(UsersRepository repository, ConfirmationsRepository confirmationsRepository) {
        this.repository = repository;
        this.confirmationsRepository = confirmationsRepository;
    }


    @Override
    public void save(User user) throws AlreadyExistsException {
        if (repository.existsByEmail(user.getEmail())) {
            throw new AlreadyExistsException();
        }
        user.setConfirmed(false);
        user.setRole(Role.USER);
        user.setCreatingDate(new Date());


        repository.save(user);
    }

    @Override
    public List<UserDTO> findAll() {
     //   return repository.findAll().stream()
     //           .map(converter::convertToDTO)
     //           .collect(Collectors.toList());
        return null;
    }

    @Override
    public Optional<UserDTO> findById(Long id) {
//        if (!repository.existsById(id)) {
//            return Optional.empty();
//        }
//        User user = repository.getById(id);
//        return Optional.of(converter.convertToDTO(user));
        return null;
    }

    @Override
    public void confirm(User user) {
        User userFromDB = repository.getById(user.getId());
        userFromDB.setConfirmed(true);
        repository.save(userFromDB);
    }
}