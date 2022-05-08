package com.webapp.bankapp.models;

import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.math.BigInteger;
import java.sql.Timestamp;

@Entity
@Table(name = "bank.credits")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class Credit {
    @Id
    @Nullable
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "id")
    private Integer id;

    @Column(nullable = false, name = "amount")
    private BigInteger amount;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "account_id")
    @ToString.Exclude
    @NonNull
    private Account account;

    @Column(nullable = false, name = "is_active")
    private Boolean is_active;

    @Column(nullable = false, name = "timestamp")
    private Timestamp timestamp;
}
