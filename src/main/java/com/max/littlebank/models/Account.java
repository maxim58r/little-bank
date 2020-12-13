package com.max.littlebank.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
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
    private Calendar calendar = Calendar.getInstance();

    @Id
    @Column(name = "account_number")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long accountNumber;

    @Column(name = "amount", nullable = false)
    private BigDecimal amount;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "opening_date",
//            insertable = false,
            updatable = false)
    @CreationTimestamp
//    @Generated(GenerationTime.INSERT)
    private Date openingDate;

    @Column(name = "validity_period")
    private Date validityPeriod;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "owner", nullable = false)
    private User user;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL,
            mappedBy = "account")
    private List<Transaction> transactions = new ArrayList<>();

//    public Date getOpeningDate() {
//        return openingDate;
//    }
//
//    public void setOpeningDate() {
//        this.openingDate = calendar.getTime();
//    }
//
//    public Date getValidityPeriod() {
//        return validityPeriod;
//    }
//
//    public void setValidityPeriod() {
//      calendar.add(Calendar.YEAR, 4);
//      this.validityPeriod = calendar.getTime();
//    }
}
