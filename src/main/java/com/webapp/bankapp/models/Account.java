package com.webapp.bankapp.models;

import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.math.BigInteger;

@Entity
@Table(name = "bank.accounts")
@Getter
@Setter
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class Account {
    @Id
    @Nullable
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "id")
    private Integer id;

    @Column(nullable = false, name = "balance")
    @NonNull
    private BigInteger balance;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "type")
    @ToString.Exclude
    @NonNull
    private AccountType type;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "branch_id")
    @ToString.Exclude
    @NonNull
    private Branch branch;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "client_id")
    @ToString.Exclude
    @NonNull
    private Client client;

    @NonNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "interest_yield_receiver")
    @ToString.Exclude
    private Account interest_yield_receiver;

    @NonNull
    @Column(nullable = false, name = "is_active")
    private Boolean is_active;
}
