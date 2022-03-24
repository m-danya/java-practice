package com.webapp.bankapp.models;


import lombok.*;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "bank.clients")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "id")
    private Integer id;

    @Column(nullable = false, name = "type")
    private String type;

    @Column(nullable = false, name = "name")
    private String name;

    @Column(nullable = true, name = "surname")
    private String surname;

    @Column(nullable = true, name = "middle_name")
    private String middle_name;

    @Column(nullable = false, name = "address")
    private String address;

    @Column(nullable = false, name = "phone_number")
    private String phone_number;

    @Column(nullable = false, name = "email")
    private String email;

    @Column(nullable = true, name = "date_of_birth")
    private Date date_of_birth;

}
