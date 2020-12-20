package com.dataanalysis.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class SellerDto {

    private Long typeId;
    private String cpf;
    private String name;
    private String salary;
}
