package com.alibou.keycloak.Entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "wallet")
public class wallet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger wallet_id;
    private int cash;
    private int creadit;
    private int total;
    private BigInteger keycloak_id;
    private String status;
    @OneToMany(mappedBy = "wallet")
    @JsonBackReference
    private Set<transactions> transactions;

    @ManyToMany( cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name = "wallet_currency",
            joinColumns = @JoinColumn(name = "wallet_id"),
            inverseJoinColumns = @JoinColumn(name = "currency_id"))
    @JsonManagedReference
    private Set<currency> currency;

    @OneToOne(mappedBy = "wallet")
    @JsonBackReference
    private user_model userModel;

}