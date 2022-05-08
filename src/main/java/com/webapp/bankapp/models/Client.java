package com.webapp.bankapp.models;


import lombok.*;
import org.springframework.lang.Nullable;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "bank.clients")
@Getter
@Setter
@ToString
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class Client {
    @Id
    @Nullable
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "id")
    private Integer id;

    @NonNull
    @Column(nullable = false, name = "type")
    private String type;

    @NonNull
    @Column(nullable = false, name = "name")
    private String name;

    @NonNull
    @Column(nullable = true, name = "surname")
    private String surname;

    @NonNull
    @Column(nullable = true, name = "middle_name")
    private String middle_name;

    @NonNull
    @Column(nullable = false, name = "address")
    private String address;

    @NonNull
    @Column(nullable = false, name = "phone_number")
    private String phone_number;

    @NonNull
    @Column(nullable = false, name = "email")
    private String email;

    @NonNull
    @Column(nullable = true, name = "date_of_birth")
    private Date date_of_birth;

}
