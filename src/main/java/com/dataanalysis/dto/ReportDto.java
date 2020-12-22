package com.dataanalysis.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ReportDto {

    private Integer clientQtd;
    private Integer sellerQtd;
    private Integer expensiveSaleId;
    private String worstSeller;
}
