package com.leverx.dealerstat.model;

import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

import java.util.Date;

@Entity
@Table(name = "dealer_comment")
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
    private Date dateOfCreating;

    @NotEmpty
    @Column(name = "approved")
    private Boolean approved;

    @NotEmpty
    @Column(name = "mark")
    private Float mark;
}
