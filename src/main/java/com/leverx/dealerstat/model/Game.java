package com.leverx.dealerstat.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Table(name = "dealer_game")
public class Game extends BaseEntity{

    @NotEmpty
    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "games")
    private List<GameObject> gameObjects;

}
