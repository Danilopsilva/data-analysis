package com.dataanalysis.config;

import com.dataanalysis.dto.Report;
import com.dataanalysis.dto.ReportOutput;
import com.dataanalysis.dto.SellerDto;
import com.dataanalysis.mapper.CustomerFieldSetMapper;
import com.dataanalysis.processor.MakeProcess;
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
    private MakeProcess process;

    @Value("file:///Users/DAnil/data/in/input*.dat")
    private Resource[] inputFiles;



    @Bean
    public MultiResourceItemReader<Report> multiResourceItemreader() throws Exception {
        MultiResourceItemReader<Report> reader = new MultiResourceItemReader<>();
        reader.setDelegate(customerItemReader());
        reader.setResources(inputFiles);
        return reader;
    }



    @Bean
    public FlatFileItemReader<Report> customerItemReader() throws Exception {

        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setDelimiter("!");
        tokenizer.setNames(new String[]{"line"});

        DefaultLineMapper<Report> customerLineMapper = getReportDefaultLineMapper(tokenizer);

        FlatFileItemReader<Report> reader = new FlatFileItemReader<>();
        reader.setLineMapper(customerLineMapper);
        return reader;
    }


    private DefaultLineMapper<Report> getReportDefaultLineMapper(DelimitedLineTokenizer tokenizer) {
        DefaultLineMapper<Report> customerLineMapper = new DefaultLineMapper<>();
        customerLineMapper.setLineTokenizer(tokenizer);
        customerLineMapper.setFieldSetMapper( new CustomerFieldSetMapper());
        customerLineMapper.afterPropertiesSet();
        return customerLineMapper;
    }


    @Bean
    public ItemWriter<Report> customerItemWriter() {
        return items -> {
                ReportOutput reportOutput = process.make((List<Report>) items);
                System.out.println(reportOutput);
            };
    }





    @Bean
    public Step step1() throws Exception {
        return stepBuilderFactory.get("step1")
                .<Report, Report>chunk(10)
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
