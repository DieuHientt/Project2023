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
    private String status;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "wallet_id", referencedColumnName = "wallet_id")
    @JsonManagedReference
    private wallet wallet;

    @OneToOne(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinColumn(name = "sv_id", referencedColumnName = "sv_id")
    @JsonManagedReference
    private saving_target savingTarget;

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    @JoinTable(name = "user_model_report",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "report_id"))
    @JsonManagedReference
    private Set<report> report;
}
//