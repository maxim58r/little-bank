package com.max.littlebank.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.max.littlebank.local_date_converter.LocalDateConverter;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;

import javax.persistence.*;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
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

    //    @Convert(converter = LocalDateConverter.class)
    @Generated(GenerationTime.INSERT)
    @Column(name = "opening_date", updatable = false)
    private LocalDateTime openingDate;

//    @Convert(converter = LocalDateConverter.class)
    @Column(name = "validity_period")
    private LocalDateTime validityPeriod;

    @ManyToOne
    @JoinColumn(name = "owner", nullable = false)
    private User owner;

    public Account(BigDecimal amount) {
        this.amount = amount;
    }
//
//    public LocalDate getOpeningDate() {
//        return openingDate;
//    }
//
//    public void setOpeningDate(LocalDate openingDate) {
//        this.openingDate = openingDate;
//    }
//
//    public LocalDate getValidityPeriod() {
//        return validityPeriod;
//    }
//
//    public void setValidityPeriod() {
//        LocalDate localDates = LocalDate.now();
//        this.validityPeriod = localDates.plusYears(4);
//    }
}
