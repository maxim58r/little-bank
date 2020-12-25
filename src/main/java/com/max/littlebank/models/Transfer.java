package com.max.littlebank.models;

import lombok.*;

import java.math.BigDecimal;

/**
 * @author Serov Maxim
 */

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Transfer {
    private long transferFromId;
    private long transferToId;
    private BigDecimal amount;
}
