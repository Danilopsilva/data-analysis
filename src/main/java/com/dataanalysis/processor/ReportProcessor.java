package com.dataanalysis.processor;

import com.dataanalysis.dto.DataDto;
import com.dataanalysis.dto.ReportDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

@Component
public class ReportProcessor {

    @Value("file:${user.home}/data/out/")
    private Resource outputFile;
    @Autowired
    private DataProcessor dataProcessor;
    
    private String EXT = ".dat";

    public String writeReport(ReportDto report){

        StringBuilder text = new StringBuilder();

        if(report != null){

            text.append("\n");
            text.append("******************************************");
            text.append("\n");
            text.append("Relatório de Vendas");
            text.append("\n");
            text.append("******************************************");
            text.append("\n");
            text.append("Quantidade de Clientes: ");
            text.append(report.getClientQtd());
            text.append("\n");
            text.append("Quantidade de Vendedores: ");
            text.append(report.getSellerQtd());
            text.append("\n");
            text.append("Identificador da Venda mais cara: ");
            text.append(report.getExpensiveSaleId());
            text.append("\n");
            text.append("Pior Vendedor: ");
            text.append(report.getWorstSeller());
            text.append("\n");
            text.append("******************************************");
            text.append("\n");

        }
        return  text.toString();
    }

    private String getFileName() throws IOException {
        String filePath = String.valueOf(outputFile.getURL().getPath());
        String fileName = "flat_file_name" + ".done" + EXT;
        return filePath.concat(fileName);
    }

    public void generate(DataDto dataDto) throws IOException {

        BufferedWriter writer = new BufferedWriter(new FileWriter(getFileName(), true));
        writer.append(writeReport(dataProcessor.getData(dataDto)));
        writer.close();
    }
}
