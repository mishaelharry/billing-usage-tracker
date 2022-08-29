package com.usage.tracker.app.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "usages")
public class Usage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @OneToOne
    private User user;

    private Integer count;

    private Double amount;

    private Integer unit;

}
