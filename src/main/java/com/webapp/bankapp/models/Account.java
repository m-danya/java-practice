package com.webapp.bankapp.models;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "bank.accounts")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "id")
    private Integer id;

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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "interest_yield_receiver")
    @ToString.Exclude
    private Account interest_yield_receiver;

    @Column(nullable = false, name = "is_active")
    private Boolean is_active;
}
