package com.chicha.carshop_admin.data.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class StatsDTO {
    int totalDeals;
    int completedDeals;
    int pendingDeals;
    int cancelledDeals;
}
