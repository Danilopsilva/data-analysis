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
public class DataDto {

    public List<SellerDto> sellers;
    public List<ClientDto> clients;
    public List<SaleDto> sales;

}
