package com.chicha.carshop_admin.data.enities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Integer id;

    @Column(name = "FullName", nullable = false, length = 100)
    private String fullName;

    @Column(name = "PassportSeries", length = 20)
    private String passportSeries;

    @Column(name = "PassportNumber", nullable = false, length = 50)
    private String passportNumber;

    @Column(name = "Adress", nullable = false, length = 100)
    private String adress;

    @Column(name = "Phone", nullable = false, length = 16)
    private String phone;

    @Column(name = "Email", nullable = false, length = 50)
    private String email;

    @Column(name = "Password", nullable = false, length = 500)
    private String password;

    @Column(name = "GeneratedPassword", length = 500)
    private String generatedPassword;

}