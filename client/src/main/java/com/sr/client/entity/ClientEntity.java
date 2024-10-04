package com.sr.client.entity;


import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import org.springframework.data.annotation.Id;

@Entity
@Table(name = "CLIENT")
@Data
public class ClientEntity extends PersonEntity implements Serializable {


    @Id
    @GeneratedValue(generator = "UUID")
    @Column(name = "CLIENT_CODE")
    private String clientId;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "STATUS")
    private Boolean status;

}
