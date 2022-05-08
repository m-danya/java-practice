package com.webapp.bankapp.models;

import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Timestamp;

@Entity
@Table(name = "bank.operations")
@Getter
@Setter
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class Operation {
    @Id
    @Nullable
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "id")
    private Integer id;

    @NonNull
    @Column(nullable = false, name = "amount")
    private BigInteger amount;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "account_id")
    @ToString.Exclude
    @NonNull
    private Account account;

    @NonNull
    @Column(nullable = false, name = "timestamp")
    private Timestamp timestamp;
}
