package com.leverx.dealerstat.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.util.Calendar;
import java.util.List;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "game_objects")
public class GameObject extends BaseEntity {

    @NotEmpty
    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

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
    private BigDecimal price;

    @OneToMany(mappedBy = "gameObject", cascade = CascadeType.ALL)
    private List<Comment> comments;

    @ManyToMany
    @JoinTable(name = "gameobject_game",
               joinColumns = @JoinColumn(name = "game_object_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "game_id", referencedColumnName = "id"))
    private List<Game> games;


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Calendar getDateOfCreating() {
        return dateOfCreating;
    }

    public void setDateOfCreating(Calendar dateOfCreating) {
        this.dateOfCreating = dateOfCreating;
    }

    public Calendar getDateOfUpdating() {
        return dateOfUpdating;
    }

    public void setDateOfUpdating(Calendar dateOfUpdating) {
        this.dateOfUpdating = dateOfUpdating;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }
}
