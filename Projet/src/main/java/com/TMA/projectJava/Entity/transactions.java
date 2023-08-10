package com.alibou.keycloak.Entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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
@Table(name = "transactions")
public class transactions {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger transaction_id;
    private int amount;
    private String note;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "card_id")
    @JsonManagedReference
    private card card;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(name = "transaction_category",
            joinColumns = @JoinColumn(name = "transaction_id"),
            inverseJoinColumns = @JoinColumn(name = "category_id"))
    @JsonIgnore
    private Set<category> category;

    @OneToMany(mappedBy = "transactions")
    @JsonBackReference
    private Set<income> income;

    @OneToMany(mappedBy = "transactions")
    @JsonBackReference
    private Set<outcome> outcome;

    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "wallet_id")
    @JsonManagedReference
    private wallet wallet;

}