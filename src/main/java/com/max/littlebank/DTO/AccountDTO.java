package com.max.littlebank.DTO;

import com.max.littlebank.models.Account;
import com.max.littlebank.models.User;
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
public class AccountDTO {
    private long accountNumber;

    private BigDecimal amount;

    private LocalDateTime openingDate;

    private LocalDateTime validityPeriod;

    private User owner;

    public AccountDTO(Account account) {
        this.accountNumber = account.getAccountNumber();
        this.amount = account.getAmount();
        this.openingDate = account.getOpeningDate();
        this.validityPeriod = account.getValidityPeriod();
        this.owner = account.getOwner();
    }
}
