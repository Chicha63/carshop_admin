package com.chicha.carshop_admin.data.DTO;

import com.chicha.carshop_admin.data.enities.Model;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class GoodDTO {
    BigDecimal price;
    String color;
    String country;
    String manufacturer;
    Model model;
    String availability;
}
