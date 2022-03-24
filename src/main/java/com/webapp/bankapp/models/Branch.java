package com.webapp.bankapp.models;

import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;

@Entity
@Table(name = "bank.branches")
@Getter
@Setter
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class Branch {
    @Id
    @Nullable
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "id")
    private Integer id;

    @NonNull
    @Column(nullable = false, name = "name")
    private String name;

    @NonNull
    @Column(nullable = false, name = "address")
    private String address;
}
