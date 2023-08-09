package com.bizboar.superCoolBatchProgram.config;

import com.bizboar.superCoolBatchProgram.domain.RALClassicColors;
import com.bizboar.superCoolBatchProgram.repositories.RALClassicColorsRepository;
import lombok.AllArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;

@Configuration
@EnableBatchProcessing
@AllArgsConstructor
public class SpringBatchConfig {

    @Autowired
    JobBuilderFactory jobBuilderFactory;

    @Autowired
    StepBuilderFactory stepBuilderFactory;

    @Autowired
    private RALClassicColorsRepository ralClassicColorsRepository;

    @Bean
    public FlatFileItemReader<RALClassicColors> reader() {
        FlatFileItemReader<RALClassicColors> itemReader = new FlatFileItemReader<>();
        itemReader.setResource(new FileSystemResource("src/main/resources/data/ral_classic.csv"));
        itemReader.setName("csvReader");
        itemReader.setLinesToSkip(1);
        itemReader.setLineMapper(lineMapper());

        return itemReader;
    }

    private LineMapper<RALClassicColors> lineMapper() {
        DefaultLineMapper<RALClassicColors> lineMapper = new DefaultLineMapper<>();

        DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(",");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames("RAL", "RGB", "HEX", "CMYK", "LRV","English");

        BeanWrapperFieldSetMapper<RALClassicColors> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(RALClassicColors.class);

        lineMapper.setLineTokenizer(lineTokenizer);
        lineMapper.setFieldSetMapper(fieldSetMapper);
        return lineMapper;

    }

    @Bean
    public RALClassicColorsDataProcessor processor() {
        return new RALClassicColorsDataProcessor();
    }

    @Bean
    public RepositoryItemWriter<RALClassicColors> writer() {
        RepositoryItemWriter<RALClassicColors> writer = new RepositoryItemWriter<>();
        writer.setRepository(ralClassicColorsRepository);
        writer.setMethodName("save");
        return writer;
    }


    @Bean
    public Step step1() {
        return stepBuilderFactory.get("csv-step").<RALClassicColors, RALClassicColors> chunk(10)
                .reader(reader())
                .processor(processor())
                .writer(writer())
                .taskExecutor(taskExecutor())
                .build();
    }

    @Bean
    public Job batchJob() {
        return jobBuilderFactory.get("colorData")
                .flow(step1())
                .end()
                .build();
    }


    @Bean
    public TaskExecutor taskExecutor() {
        SimpleAsyncTaskExecutor asyncTaskExecutor = new SimpleAsyncTaskExecutor();
        asyncTaskExecutor.setConcurrencyLimit(10);

        return asyncTaskExecutor;
    }



}
