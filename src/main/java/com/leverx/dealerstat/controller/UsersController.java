package com.leverx.dealerstat.controller;

import com.leverx.dealerstat.converter.UsersConverter;
import com.leverx.dealerstat.dto.UserDTO;
import com.leverx.dealerstat.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class UsersController {

    private final UsersService service;
    private final UsersConverter converter;

    @Autowired
    public UsersController(UsersService service, UsersConverter converter) {
        this.service = service;
        this.converter = converter;
    }


    @GetMapping("/users")
    public ResponseEntity<UserDTO> getAllUsers() {
        List<UserDTO> userDTOS = service.findAll().stream()
                .map(converter::convertToDTO).collect(Collectors.toList());
        return ResponseEntity.ok(userDTOS.get(0));
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<List<UserDTO>> getUser(@PathVariable("id") Long id) {
        Optional<UserDTO> userDTO = service.findById(id);
        if (userDTO.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(null);
    }


}
