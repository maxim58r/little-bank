package com.max.littlebank.models;

import com.max.littlebank.DTO.AccountDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

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

    @Column(name = "opening_date", updatable = false,
            insertable = false,
            columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime openingDate;

    @Column(name = "validity_period", updatable = false,
            columnDefinition = "DATETIME DEFAULT NULL")
    private LocalDateTime validityPeriod;

    @ManyToOne
    @JoinColumn(name = "owner", nullable = false)
    private User owner;

    public Account(BigDecimal amount) {
        this.amount = amount;
    }

    public Account(AccountDTO accountDTO ) {
        this.accountNumber = accountDTO.getAccountNumber();
        this.amount = accountDTO.getAmount();
        this.openingDate = accountDTO.getOpeningDate();
        this.validityPeriod = accountDTO.getValidityPeriod();
        this.owner = accountDTO.getOwner();
    }
}
