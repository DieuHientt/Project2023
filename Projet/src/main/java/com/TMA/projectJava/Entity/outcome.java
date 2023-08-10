package com.alibou.keycloak.Entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
import java.util.Date;
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "outcome")
public class outcome {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger outcome_id;
    private Date date_time;
    private String status;
    @ManyToOne ( cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "transaction_id")
    @JsonManagedReference
    private transactions transactions;
}