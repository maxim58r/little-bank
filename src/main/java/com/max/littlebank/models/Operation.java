package com.max.littlebank.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * @author Serov Maxim
 */

@Getter
@Setter
@NoArgsConstructor
public class Operation {
    private long id;
    private BigDecimal amount;
}
