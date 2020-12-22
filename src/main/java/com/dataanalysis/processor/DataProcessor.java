package com.dataanalysis.processor;

import com.dataanalysis.dto.*;
import com.dataanalysis.mapper.DataMapper;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.ArrayList;
import java.util.stream.Collectors;

@Component
public class DataProcessor {

    private static final String SELLER_ID = "001";

    private static final String CLIENT_ID = "002";

    private static final String SALE_ID = "003";

    List<SellerDto> sellerList = new ArrayList<>();
    List<ClientDto> clientList = new ArrayList<>();
    List<SaleDto> saleList = new ArrayList<>();

    public String[] separator(File item){
        String[] list = item.getLine().split("ç");
        return list;
    }

    public DataDto filter(List<File> data){

        for (File file : data){
            String[] array = separator(file);

            if(array[0].equals(SELLER_ID)) {
                     sellerList.add(DataMapper.makeSeller(array));
                 }else if(array[0].equals(CLIENT_ID)){
                     clientList.add(DataMapper.makeClient(array));
                }else if(array[0].equals(SALE_ID)){
                     saleList.add(DataMapper.makeSale(array));
                 }
            }

        DataDto dataDto = new DataDto();
       return dataDto.builder()
                    .sellers(sellerList)
                    .clients(clientList)
                    .sales(saleList)
                    .build();
    }

    public ReportDto getData(DataDto data){
        ReportDto reportDto = new ReportDto();

        SaleDto biggestSale = data.getSales().stream().max(Comparator.comparing(SaleDto::getTotalValue)).orElse(null);

        String worstSeller = data.getSales().stream().sorted(Comparator.comparingDouble(SaleDto::getTotalValue))
                .collect(Collectors.toList()).get(0).getSalesmanName();

       return  reportDto.builder()
                .clientQtd(data.getClients().size())
                .sellerQtd(data.getSellers().size())
                .expensiveSaleId(biggestSale.getSaleId())
                .worstSeller(worstSeller)
                .build();
    }
}
