package com.dataanalysis.mapper;

import com.dataanalysis.dto.ClientDto;
import com.dataanalysis.dto.ItemDto;
import com.dataanalysis.dto.SaleDto;
import com.dataanalysis.dto.SellerDto;
import java.util.*;

public class BuilderDataMapper {

    private static final String ITEM_SPLIT = "-";
    private static final String ITEM_SEPARATOR = ",";


    private static double mountItems(List<ItemDto> items, double totalValueSale, String values) {
        if(!values.isEmpty()){
            values = values.replace("[","").replace("]", "");
            List<String> itemList = Arrays.asList(values.split(ITEM_SEPARATOR));
            for (String item : itemList) {
                List<String> itemValues = Arrays.asList(item.split(ITEM_SPLIT));
                items.add(ItemDto.builder()
                        .id(Long.valueOf(itemValues.get(0)))
                        .quantity(Integer.parseInt(itemValues.get(1)))
                        .price(Double.parseDouble(itemValues.get(2)))
                        .build());
                totalValueSale += Double.parseDouble(itemValues.get(2));
            }
        }
        return totalValueSale;
    }

    public static SaleDto makeSale(String[] data){
        double totalSale = 0;
        List<ItemDto> items = new ArrayList<>();
        String values = data[2];
        totalSale = mountItems(items, totalSale, values);

        return  SaleDto.builder()
                .typeId(Long.parseLong(data[0]))
                .saleId(Integer.parseInt(data[1]))
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
