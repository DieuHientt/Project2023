package com.TMA.projectJava.Entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
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
    @JsonManagedReference
    private Set<wallet> wallet;
}
