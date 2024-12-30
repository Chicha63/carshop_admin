package com.chicha.carshop_admin.data.enities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateHistoryId implements Serializable {
    private Integer dealId;
    private Integer statusId;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UpdateHistoryId that = (UpdateHistoryId) o;
        return Objects.equals(dealId, that.dealId) && Objects.equals(statusId, that.statusId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(dealId, statusId);
    }
}
