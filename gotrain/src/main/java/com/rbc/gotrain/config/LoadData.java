package com.rbc.gotrain.config;


import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rbc.gotrain.models.Train;
import com.rbc.gotrain.repositories.TrainRepository;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Configuration
@AllArgsConstructor
public class LoadData {

    private final ResourceLoader resourceLoader;
    private static final Logger log = LoggerFactory.getLogger(LoadData.class);

    @Bean
    CommandLineRunner loadDatabase(TrainRepository trainRepository) {
        return args -> {
            trainRepository.saveAll(this.loadTrains());
        };

    }

    private List<Train> loadTrains() throws IOException {
        Resource resource = resourceLoader.getResource("classpath:data.json");
        InputStream inputStream = resource.getInputStream();
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(inputStream, new TypeReference<List<Train>>(){});
    }

}
