package com.leverx.dealerstat.model;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "confirmations")
public class Confirmation extends BaseEntity{

    @NotEmpty
    @Column(name = "hash_code")
    private String hashCode;

    @Column(name = "expiration_time")
    private Date expirationTime;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

}
