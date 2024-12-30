package com.chicha.carshop_admin.data.enities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.Instant;

@Getter
@Setter
@Entity
public class Deal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "Good_Id", nullable = false)
    private Good good;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "Client_Id", nullable = false)
    private Client client;

    @Column(name = "\"Date\"", nullable = false)
    private Instant date;

    @Column(name = "Delivery", nullable = false)
    private Boolean delivery = false;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "Payment_Id", nullable = false)
    private Payment payment;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "Employee_Id")
    private Employee employee;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "Status_Id", nullable = false)
    private Status status;

    @Column(name = "FinalPrice", nullable = false, precision = 10, scale = 2)
    private BigDecimal finalPrice;

}