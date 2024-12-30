package com.chicha.carshop_admin.data.enities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Entity
public class Model {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Integer id;

    @Column(name = "\"Year\"", nullable = false)
    private Integer year;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "Characteristics_Id", nullable = false)
    private Characteristics characteristics;

    @Column(name = "Price", nullable = false, precision = 10, scale = 2)
    private BigDecimal price;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "ModelName_Id", nullable = false)
    private ModelName modelName;

}