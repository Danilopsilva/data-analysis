package com.dataanalysis.config;

import com.dataanalysis.dto.File;
import com.dataanalysis.mapper.CustomerFieldSetMapper;
import com.dataanalysis.processor.DataProcessor;
import com.dataanalysis.processor.ReportProcessor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.MultiResourceItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import java.util.*;

@Configuration
@EnableBatchProcessing
public class ImporterFile {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private DataProcessor dataProcessor;

    @Autowired
    private ReportProcessor reportProcessor;


    @Value("file:${user.home}/data/in/*.dat")
    private Resource[] inputFiles;


    @Bean
    public MultiResourceItemReader<File> multiResourceItemreader() throws Exception {
        MultiResourceItemReader<File> reader = new MultiResourceItemReader<>();
        reader.setDelegate(customerItemReader());
        reader.setResources(inputFiles);
        return reader;
    }

    @Bean
    public FlatFileItemReader<File> customerItemReader() throws Exception {

        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setDelimiter("!");
        tokenizer.setNames(new String[]{"line"});

        DefaultLineMapper<File> customerLineMapper = getReportDefaultLineMapper(tokenizer);

        FlatFileItemReader<File> reader = new FlatFileItemReader<>();
        reader.setLineMapper(customerLineMapper);
        return reader;
    }


    private DefaultLineMapper<File> getReportDefaultLineMapper(DelimitedLineTokenizer tokenizer) {
        DefaultLineMapper<File> customerLineMapper = new DefaultLineMapper<>();
        customerLineMapper.setLineTokenizer(tokenizer);
        customerLineMapper.setFieldSetMapper( new CustomerFieldSetMapper());
        customerLineMapper.afterPropertiesSet();
        return customerLineMapper;
    }

    @Bean
    public ItemWriter<File> customerItemWriter() {
        return items -> {
            reportProcessor.generate(dataProcessor.filter((List<File>) items));
        };
    }

    @Bean
    public Step step1() throws Exception {
        return stepBuilderFactory.get("step1")
                .<File, File>chunk(100000)
                .reader(multiResourceItemreader())
                .writer(customerItemWriter())
                .build();
    }

    @Bean
    public Job job() throws Exception {
        return jobBuilderFactory.get("job")
                .start(step1())
                .build();
    }
}
