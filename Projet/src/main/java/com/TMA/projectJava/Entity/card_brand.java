package com.TMA.projectJava.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigInteger;
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
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
