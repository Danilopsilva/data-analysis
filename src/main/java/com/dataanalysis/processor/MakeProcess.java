package com.dataanalysis.processor;

import com.dataanalysis.dto.*;
import com.dataanalysis.util.BuilderData;
import org.springframework.stereotype.Component;
import java.text.ParseException;
import java.util.*;
import java.util.ArrayList;

@Component
public class MakeProcess  {

    private static final String SELLER_ID = "001";

    private static final String CLIENT_ID = "002";

    private static final String SALE_ID = "003";

    List<SellerDto> sellerList = new ArrayList<>();
    List<ClientDto> clientList = new ArrayList<>();
    List<SaleDto> saleList = new ArrayList<>();

    public String[] separator(Report item){
        String[] list = item.getLine().split("ç");
        return list;
    }

    public ReportOutput make(List<Report> data){

        for (Report report : data){
                 String[] array = separator(report);

                 if(array[0].equals(SELLER_ID)) {
                     sellerList.add(BuilderData.makeSeller(array));
                 }else if(array[0].equals(CLIENT_ID)){
                     clientList.add(BuilderData.makeClient(array));
                } else if(array[0].equals(SALE_ID)){
                     saleList.add(BuilderData.makeSale(array));
                 }
            }

        ReportOutput reportOutput = new ReportOutput();
       return reportOutput.builder()
                    .sellers(sellerList)
                    .clients(clientList)
                    .sales(saleList)
                    .build();

    }
}
