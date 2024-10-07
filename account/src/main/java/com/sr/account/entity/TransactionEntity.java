package com.sr.account.entity;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * transaction entity
 *
 * @author srueda
 */
@Entity
@Table(name = "TRANSACTION")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TransactionEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "CODE")
    private UUID id;

    @Column(name = "TRANSACTION_DATE")
    private LocalDateTime transactionDate;

    @Column(name = "TRANSACTION_TYPE")
    private String transactionType;

    @Column(name = "AMOUNT")
    private double amount;

    @Column(name = "BALANCE")
    private double balance;

    @ManyToOne
    private AccountEntity account;

    @PrePersist
    private void prePersist() {
        if (this.transactionDate == null) {
            this.transactionDate = LocalDateTime.now();
        }
    }

}
