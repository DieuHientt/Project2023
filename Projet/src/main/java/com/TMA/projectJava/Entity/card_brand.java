package com.alibou.keycloak.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "card_brand")
public class card_brand {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger brand_id;
    private String name_brand;
    private String status;
    @OneToOne(mappedBy = "card_brand")
    @JsonBackReference
    private card card;
}