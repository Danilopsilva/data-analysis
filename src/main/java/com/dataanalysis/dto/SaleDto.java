package com.dataanalysis.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class SaleDto {

    private Long typeId;
    private Integer saleId;
    private List<ItemDto> itemList;
    private String salesmanName;
    private Double totalValue;

}
