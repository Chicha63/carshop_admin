package com.chicha.carshop_admin.data.enities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "Characteristics")
public class Characteristics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "BodyType_Id", nullable = false)
    private BodyType bodyType;

    @Column(name = "DoorsCount", nullable = false)
    private Integer doorsCount;

    @Column(name = "PlacesCount", nullable = false)
    private Integer placesCount;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "Engine_Id", nullable = false)
    private Engine engine;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "EnginePlacement_Id", nullable = false)
    private EnginePlacement enginePlacement;

}