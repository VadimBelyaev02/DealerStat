package com.leverx.dealerstat.service.impl;

import com.leverx.dealerstat.exception.AlreadyExistsException;
import com.leverx.dealerstat.exception.NotFoundException;
import com.leverx.dealerstat.model.Role;
import com.leverx.dealerstat.model.User;
import com.leverx.dealerstat.repository.ConfirmationsRepository;
import com.leverx.dealerstat.repository.UsersRepository;
import com.leverx.dealerstat.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class UsersServiceImpl implements UsersService {

    private final UsersRepository repository;
    private final ConfirmationsRepository confirmationsRepository;
    private final PasswordEncoder encoder;

    @Autowired
    public UsersServiceImpl(UsersRepository repository,
                            ConfirmationsRepository confirmationsRepository,
                            PasswordEncoder encoder) {
        this.repository = repository;
        this.confirmationsRepository = confirmationsRepository;
        this.encoder = encoder;
    }


    @Override
    public void save(User user) throws AlreadyExistsException {
        if (repository.existsByEmail(user.getEmail())) {
            throw new AlreadyExistsException();
        }

        user.setPassword(encoder.encode(user.getPassword()));
        user.setConfirmed(false);
        user.setRole(Role.USER);
        user.setCreatingDate(new Date());

        repository.save(user);
    }

    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public User findById(Long id) {
        return repository.getById(id);
    }

    @Override
    public void confirm(User user) {
        User userFromDB = repository.getById(user.getId());
        userFromDB.setConfirmed(true);
        repository.save(userFromDB);
    }

    @Override
    public User findByEmail(String email) throws NotFoundException {
        return repository.findByEmail(email).orElseThrow(() -> {
            throw new UsernameNotFoundException("User doesn't exist");
        });
    }

    @Override
    public void recoverPassword(User user, String password) {
        User userFromDB = repository.getById(user.getId());
        userFromDB.setPassword(password);
        repository.save(userFromDB);
    }

    @Override
    public void becomeTrader(User user) {
        User userFromDB = repository.getById(user.getId());
        userFromDB.setRole(Role.TRADER);
        repository.save(userFromDB);
    }
}
