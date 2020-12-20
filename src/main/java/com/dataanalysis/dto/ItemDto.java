package com.dataanalysis.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ItemDto {

    private Long id;
    private Integer quantity;
    private Double price;
}
