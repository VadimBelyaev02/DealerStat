package com.leverx.dealerstat.model;

import com.leverx.dealerstat.dto.Status;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Calendar;
import java.util.List;

@Entity
@Table(name = "dealer_game_object")
public class GameObject extends BaseEntity {

    @NotEmpty
    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @NotEmpty
    @Column(name = "status")
    private Status status;

    @NotEmpty
    @ManyToOne()
    @JoinColumn(name = "author_id")
    private User author;

    @NotEmpty
    @CreatedDate
    @Column(name = "created_at")
    private Calendar dateOfCreating;

    @Column(name = "updated_at")
    @LastModifiedDate
    private Calendar dateOfUpdating;

    @NotEmpty
    @Column(name = "price")
    private Integer price;

    @OneToMany(mappedBy = "gameObject")
    private List<Comment> comments;

    @ManyToMany
    @JoinTable(name = "dealer_gameobject_game",
               joinColumns = @JoinColumn(name = "game_object_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "game_id", referencedColumnName = "id"))
    private List<Game> games;

}
