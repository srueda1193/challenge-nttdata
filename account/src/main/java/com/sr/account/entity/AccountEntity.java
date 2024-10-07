package com.sr.account.entity;


import java.io.Serializable;
import java.util.UUID;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


/**
 * Account entity
 *
 * @author srueda
 */
@Entity
@Table(name = "ACCOUNT")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "CODE")
    private UUID id;

    @Column(name = "ACCOUNT_NUMBER")
    private String accountNumber;

    @Column(name = "ACCOUNT_TYPE")
    private String accountType;

    @Column(name = "BALANCE")
    private double balance;

    @Column(name = "STATUS")
    private boolean status;

    @Column(name = "CLIENT_ID")
    private Long clientId;

}
