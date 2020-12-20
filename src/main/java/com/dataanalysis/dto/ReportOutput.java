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
public class ReportOutput {

    List<SellerDto> sellers;

    List<ClientDto> clients;

    List<SaleDto> sales;

}
