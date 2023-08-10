package com.alibou.keycloak.Entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.Set;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "currency")
public class currency {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger currency_id;
    private String name_currency;
    private String status;
    @ManyToMany(mappedBy = "currency")
    @JsonIgnoreProperties("currency")
    private Set<wallet> wallet;
}
