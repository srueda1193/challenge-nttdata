package com.sr.client.entity;

import java.io.Serializable;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

@Entity
@Table(name = "CLIENT")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@PrimaryKeyJoinColumn(name ="CLIENT_CODE", referencedColumnName = "CODE")
public class ClientEntity extends PersonEntity implements Serializable {

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "STATUS")
    private Boolean status;

}
