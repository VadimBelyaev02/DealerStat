package com.leverx.dealerstat.model;

import com.leverx.dealerstat.dto.Role;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import java.util.Calendar;
import java.util.List;

@Entity
@Table(name = "dealer_user")
@NoArgsConstructor
public class User extends BaseEntity{

    @NotEmpty
    @Column(name = "first_name")
    private String first_name;

    @NotEmpty
    @Column(name = "last_name")
    private String last_name;

    @NotEmpty
    @Column(name = "hash_password")
    private String password;

    @Email
    @Column(name = "email")
    private String email;

    @NotEmpty
    @Column(name = "created_at")
    private Calendar dateOfCreating;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<Comment> comments;

    @OneToMany(mappedBy = "author")
    private List<GameObject> gameObjects;
}
