package com.dataanalysis.mapper;

import com.dataanalysis.dto.Report;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.stereotype.Component;

@Component
public class CustomerFieldSetMapper implements FieldSetMapper<Report> {


    @Override
    public Report mapFieldSet(FieldSet fieldSet) {

        return Report.builder()
                .line(fieldSet.readString("line"))
                .build();
    }
}