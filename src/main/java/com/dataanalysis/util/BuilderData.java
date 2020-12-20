package com.dataanalysis.util;

import com.dataanalysis.dto.ClientDto;
import com.dataanalysis.dto.ItemDto;
import com.dataanalysis.dto.SaleDto;
import com.dataanalysis.dto.SellerDto;
import java.util.*;

public class BuilderData {

    private static final String ITEM_SEPARATOR = ",";
    private static final String ITEM_VALUE_SPLIT = "-";


    public static SaleDto makeSale(String[] data){
        List<ItemDto> items = new ArrayList<>();
        double totalSale = 0;

        String values = data[2];

        if(!values.isEmpty()){
            values = values.replace("[","").replace("]", "");
            List<String> itemList = Arrays.asList(values.split(ITEM_SEPARATOR));
            for (String value : itemList) {
                List<String> itemValues = Arrays.asList(value.split(ITEM_VALUE_SPLIT));
                items.add(ItemDto.builder()
                        .id(Long.valueOf(itemValues.get(0)))
                        .quantity(Integer.parseInt(itemValues.get(1)))
                        .price(Double.parseDouble(itemValues.get(2)))
                        .build());
                totalSale += Double.parseDouble(itemValues.get(2));
            }
        }

        return  SaleDto.builder()
                .typeId(Long.parseLong(data[0]))
                .saleId(Long.parseLong(data[1]))
                .itemList(items)
                .salesmanName(data[3])
                .totalValue(totalSale)
                .build();
    }

    public static ClientDto makeClient(String[] data){
        return  ClientDto.builder()
                .typeId(Long.parseLong(data[0]))
                .cnpj(data[1])
                .name(data[2])
                .businessArea(data[3])
                .build();
    }

    public static SellerDto makeSeller(String[] data){
        return  SellerDto.builder()
                .typeId(Long.parseLong(data[0]))
                .cpf(data[1])
                .name(data[2])
                .salary(data[3])
                .build();
    }
}
