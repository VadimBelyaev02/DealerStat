package com.leverx.dealerstat.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Entity
@Table(name = "games")
public class Game extends BaseEntity{

    @NotEmpty
    @Column(name = "name")
    private String name;

    @ManyToMany(mappedBy = "games")
    private List<GameObject> gameObjects;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<GameObject> getGameObjects() {
        return gameObjects;
    }

    public void setGameObjects(List<GameObject> gameObjects) {
        this.gameObjects = gameObjects;
    }
}
