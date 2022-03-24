package com.webapp.bankapp.models;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "bank.account_types")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class AccountType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "id")
    private Integer id;

    @Column(nullable = false, name = "name")
    private String name;

    @Column(nullable = false, name = "credit_limit")
    private BigDecimal credit_limit;

    @Column(nullable = true, name = "credit_interval")
    private Integer credit_interval;

    @Column(nullable = true, name = "interest_yield_percent_per_year")
    private BigDecimal interest_yield_percent_per_year;

    @Column(nullable = true, name = "interest_yield_interval")
    private Integer interest_yield_interval;
}
