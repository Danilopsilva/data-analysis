package com.dataanalysis.mapper;

import com.dataanalysis.dto.File;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.stereotype.Component;

@Component
public class CustomerFieldSetMapper implements FieldSetMapper<File> {

    @Override
    public File mapFieldSet(FieldSet fieldSet) {
        return File.builder()
                .line(fieldSet.readString("line"))
                .build();
    }
}