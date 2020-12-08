package com.max.littlebank.models;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author Serov Maxim
 */

@Data
@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @Column(name = "account_number")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long accountNumber;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "opening_date", nullable = false)
    private Date openingDate;

    @Column(name = "validity_period")
    private Date validityPeriod;

    @ManyToOne
    @Column(name = "owner", nullable = false)
    private User user;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "account")
    private List<Transaction> transactions ;


}
