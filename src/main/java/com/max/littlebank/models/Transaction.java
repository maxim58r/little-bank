package com.max.littlebank.models;

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

    @Column(name = "ammount", nullable = false)
    private BigDecimal ammount;

    @Generated(GenerationTime.INSERT)
    @Column(name = "date_time", updatable = false)
    private LocalDateTime dateTime;

    @ManyToOne
    private Account account;
}
