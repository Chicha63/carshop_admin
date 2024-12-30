package com.chicha.carshop_admin.data.DTO;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EmployeeDTO {
    private Integer id;
    private String fullname;
    private Integer accessright;
    private String email;
    private String password;
}
