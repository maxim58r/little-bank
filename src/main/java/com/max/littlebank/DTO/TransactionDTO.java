package com.max.littlebank.DTO;

import com.max.littlebank.models.Account;
import com.max.littlebank.models.Transaction;
import com.max.littlebank.models.TransactionType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author Serov Maxim
 */

@Getter
@Setter
@NoArgsConstructor
public class TransactionDTO {
    private long id;
    private String type;
    private BigDecimal amount;
    private LocalDateTime dateTime;
    private Account account;

    public TransactionDTO(Transaction transaction) {
        this.id = transaction.getId();
        this.type = transaction.getType();
        this.amount = transaction.getAmount();
        this.dateTime = transaction.getDateTime();
        this.account = transaction.getAccount();
    }
}
