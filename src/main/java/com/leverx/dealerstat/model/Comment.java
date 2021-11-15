package com.leverx.dealerstat.model;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "comments")
public class Comment extends BaseEntity {

    @NotEmpty
    @Column(name = "message")
    private String message;

    @NotEmpty
    @ManyToOne()
    @JoinColumn(name = "gameobject_id")
    private GameObject gameObject;

    @NotEmpty
    @ManyToOne()
    @JoinColumn(name = "author_id")
    private User user;

    @NotEmpty
    @Column(name = "created_at")
    @CreatedDate
    private Calendar dateOfCreating;

    @NotEmpty
    @Column(name = "approved")
    private Boolean approved;

    @NotEmpty
    @Column(name = "mark")
    private Float mark;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public GameObject getGameObject() {
        return gameObject;
    }

    public void setGameObject(GameObject gameObject) {
        this.gameObject = gameObject;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Calendar getDateOfCreating() {
        return dateOfCreating;
    }

    public void setDateOfCreating(Calendar dateOfCreating) {
        this.dateOfCreating = dateOfCreating;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    public Float getMark() {
        return mark;
    }

    public void setMark(Float mark) {
        this.mark = mark;
    }
}
