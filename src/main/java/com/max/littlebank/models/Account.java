package com.max.littlebank.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Serov Maxim
 */

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @Column(name = "account_number")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long accountNumber;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "opening_date",
//            insertable = false,
            updatable = false)
    @CreationTimestamp
//    @Generated(GenerationTime.INSERT)
    private Date openingDate;

    @Column(name = "validity_period")
    private Date validityPeriod;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "owner", nullable = false)
    private User user;

    @OneToMany(cascade = CascadeType.ALL,
            mappedBy = "account")
    private List<Transaction> transactions = new ArrayList<>();

}
