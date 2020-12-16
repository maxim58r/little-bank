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
public class Transfer {
   private long transferFromId;
    private long transferToId;
    private BigDecimal amount;
}
