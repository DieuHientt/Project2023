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
@Table(name = "report")
public class report {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger report_id;
    private String name_report;
    private String type_report;
    private String status;

    @ManyToMany(mappedBy = "report")
    @JsonManagedReference
    private Set<user_model> userModel;
}
