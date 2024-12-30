package com.chicha.carshop_admin.data.enities;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@IdClass(PossibleColorId.class)
public class PossibleColor {
    @Id
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "Model_Id", nullable = false)
    private Model model;

    @Id
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "Color_Id", nullable = false)
    private Color color;
}

class PossibleColorId implements Serializable {
    private Integer model;
    private Integer color;

    // Equals and HashCode
}