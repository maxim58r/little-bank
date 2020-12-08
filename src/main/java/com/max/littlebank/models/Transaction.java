package com.max.littlebank.models;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Serov Maxim
 */

@Data
@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "ammount", nullable = false)
    private BigDecimal ammount;

    @Column(name = "date_time")
    private Date dateTime;

    @ManyToOne
    private Account account;
}
