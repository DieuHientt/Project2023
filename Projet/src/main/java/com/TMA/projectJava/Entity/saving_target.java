package com.alibou.keycloak.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
@Table(name = "saving_target")
public class saving_target {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private BigInteger sv_id;
    private String name_sv;
    private String describe_sv;
    private Date start_date;
    private Date end_date;
    private String status;
    @OneToOne(mappedBy = "savingTarget")
    @JsonBackReference
    private user_model userModel;
}
