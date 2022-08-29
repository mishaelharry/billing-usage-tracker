package com.usage.tracker.app.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "prices")
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    private Integer min;

    private Integer max;

    private Double amount;

    private Integer quota;

}
