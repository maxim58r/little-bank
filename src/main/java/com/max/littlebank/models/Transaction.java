package com.max.littlebank.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Serov Maxim
 */

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "transaction")
public class Transaction {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "ammount", nullable = false)
    private BigDecimal ammount;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date_time",
            updatable = false)
    @CreationTimestamp
//    @Generated(GenerationTime.INSERT)
    private Date dateTime;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "account", nullable = false)
    private Account account;
}
