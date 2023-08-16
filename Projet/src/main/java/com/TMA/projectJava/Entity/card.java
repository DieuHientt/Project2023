package com.TMA.projectJava.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.Set;
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = "card")
public class card {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger card_id;
    private int amount;
    private String card_number;
    private String symbol;
    private String status;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "brand_id")
    @JsonManagedReference
    private card_brand card_brand;

    @OneToMany(mappedBy = "card")
    @JsonBackReference
    private Set<transactions> transactions;
}
