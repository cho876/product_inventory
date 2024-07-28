package com.toy.project.product.batch;

import com.toy.project.product.batch.dummy.DummyData;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
public class CsvFileJobConfig {

    private final CsvFileReader csvFileReader;
    private final CsvFileWriter csvFileWriter;

    @Bean
    public Job csvFileSaveJob(JobRepository jobRepository, Step csvFileSaveStep) {
        return new JobBuilder("csvFileSaveJob", jobRepository)
                .start(csvFileSaveStep)
                .build();
    }

    @Bean
    public Step csvFileSaveStep(
            JobRepository jobRepository,
            PlatformTransactionManager platformTransactionManager) {
        return new StepBuilder("csvFileSaveStep", jobRepository)
                .<DummyData, DummyData>chunk(100, platformTransactionManager)
                .reader(csvFileReader.csvScheduleReader())
                .writer(csvFileWriter)
                .build();
    }
}
