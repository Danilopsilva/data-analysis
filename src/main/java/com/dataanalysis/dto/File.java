package com.dataanalysis.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.batch.item.ResourceAware;
import org.springframework.core.io.Resource;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class File implements ResourceAware{

    private String line;
    private Resource resource;
}
