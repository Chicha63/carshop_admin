package com.chicha.carshop_admin.data.enities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
public class Good {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "Country_Id", nullable = false)
    private Country country;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "Model_Id", nullable = false)
    private Model model;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "Availability_Id", nullable = false)
    private Availability availability;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "Color_Id", nullable = false)
    private Color color;

    @Column(name = "Price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

}