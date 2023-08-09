package com.alibou.keycloak.entity;


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
@Table(name = "user_model")
public class user_model {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger user_id;
    private String email;
    private String email_verified;
    private String enabled;
    private String first_name;
    private String last_name;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "wallet_id", referencedColumnName = "wallet_id")
    private wallet wallet;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "sv_id", referencedColumnName = "sv_id")
    private saving_target savingTarget;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_model_report",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "report_id"))
    @JsonManagedReference
    private Set<report> report;
}