package com.leverx.dealerstat.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import java.util.Calendar;
import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
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
    private User author;

    @NotEmpty
    @Column(name = "created_at")
    @Temporal(TemporalType.DATE)
    private Date creatingDate;

    @NotEmpty
    @Column(name = "approved")
    private Boolean approved;

    @NotEmpty
    @Column(name = "rate")
    private Float rate;

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

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Date getCreatingDate() {
        return creatingDate;
    }

    public void setCreatingDate(Date creatingDate) {
        this.creatingDate = creatingDate;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }

    public Float getRate() {
        return rate;
    }

    public void setRate(Float rate) {
        this.rate = rate;
    }
}
