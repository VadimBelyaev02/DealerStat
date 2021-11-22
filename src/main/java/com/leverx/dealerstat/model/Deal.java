package com.leverx.dealerstat.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "deals")
public class Deal extends BaseEntity{

    @NotEmpty
    @Column(name = "date")
    private Date date;

    @ManyToOne
    @JoinColumn(name = "from_id")
    private User fromUser;

    @ManyToOne
    @JoinColumn(name = "to_id")
    private User toUser;
}
