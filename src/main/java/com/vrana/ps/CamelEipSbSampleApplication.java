package com.vrana.ps;

import com.vrana.ps.camel.processor.SampleProcessor;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class CamelEipSbSampleApplication {
    public static void main(String[] args) {
        SpringApplication.run(CamelEipSbSampleApplication.class, args);
    }
   /* @Bean(name = "sampleProcessor")
    public SampleProcessor getSampleProcessor() {
        return new SampleProcessor();
    }*/
}

