package com.max.littlebank.models;

import com.max.littlebank.DTO.TransactionDTO;
import lombok.Builder;
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
@Table(name = "transactions")
public class Transaction {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "type"/*, nullable = false*/)
    private String type;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Column(name = "date_time", updatable = false,
            insertable = false,
            columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime dateTime;

    @ManyToOne
    private Account account;


      public Transaction(TransactionDTO transactionDTO) {
          this.id = transactionDTO.getId();
          this.type = transactionDTO.getType();
          this.amount = transactionDTO.getAmount();
          this.dateTime = transactionDTO.getDateTime();
          this.account = transactionDTO.getAccount();
    }
}
