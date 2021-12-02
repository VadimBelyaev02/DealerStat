package com.leverx.dealerstat.service;

import com.leverx.dealerstat.exception.AlreadyExistsException;
import com.leverx.dealerstat.model.User;
import com.leverx.dealerstat.repository.UsersRepository;
import com.leverx.dealerstat.service.impl.UsersServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class UsersServiceUnitTest {

  //  @Mock
    @InjectMocks
    private UsersServiceImpl usersService;

    @Mock
    private UsersRepository usersRepository;


    @Test
    public void shouldReturnUserById() {
        User user = new User();
        user.setId(7L);
        Mockito.when(usersService.findById(7L)).thenReturn(user);

        user = usersService.findById(7L);
        assertNotNull(user);
    }

    @Test
    public void shouldReturnAllUsers() {
        List<User> users = new ArrayList<>();
        users.add(new User());

        Mockito.when(usersService.findAll()).thenReturn(users);

        assertNotNull(usersService.findAll());
    }

    @Test
    public void shouldReturnUserByEmail() {
        String email = "vadimbealev002@gmail.com";
        User user = new User();
        user.setEmail(email);

        Mockito.when(usersService.findByEmail(email)).thenReturn(user);

        assertNotNull(usersService.findByEmail(email));
    }

    @Test
    public void shouldThrowExceptionDuringSaving() {
        String email = "vadimbelaev002@gmail.com";
        User user = new User();
        user.setEmail(email);
        Mockito.when(usersRepository.existsByEmail(email)).thenReturn(true);
        assertThrows(AlreadyExistsException.class, () -> usersService.save(user));

    }

}

/*
    void save(User user) throws AlreadyExistsException;

    List<User> findAll();

    User findById(Long id);

    void confirm(User user);

    User findByEmail(String email) throws NotFoundException;

    void recoverPassword(User user, String password);

    void becomeTrader(User user);
 */