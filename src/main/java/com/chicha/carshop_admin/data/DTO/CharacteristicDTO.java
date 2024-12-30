package com.chicha.carshop_admin.data.DTO;

import com.chicha.carshop_admin.data.enities.BodyType;
import com.chicha.carshop_admin.data.enities.Engine;
import com.chicha.carshop_admin.data.enities.EnginePlacement;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CharacteristicDTO {
    BodyType bodyType;
    Integer doorsCount;
    Integer placesCount;
    Engine engine;
    EnginePlacement enginePlacement;
}
