package com.dataanalysis.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ClientDto {

    private Long typeId;
    private String cnpj;
    private String name;
    private String businessArea;

}
