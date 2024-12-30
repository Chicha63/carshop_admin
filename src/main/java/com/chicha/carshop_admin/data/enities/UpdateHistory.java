package com.chicha.carshop_admin.data.enities;

import jakarta.persistence.*;
import lombok.*;

import java.time.Instant;

@Getter
@Setter
@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@IdClass(UpdateHistoryId.class)
public class UpdateHistory {
    @Id
    @Column(name = "Deal_Id", nullable = false)
    private Integer dealId;

    @Id
    @Column(name = "Status_Id", nullable = false)
    private Integer statusId;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "Deal_Id", insertable = false, updatable = false)
    private Deal deal;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "Status_Id", insertable = false, updatable = false)
    private Status status;

    @Column(name = "\"Date\"", nullable = false)
    private Instant date;
}