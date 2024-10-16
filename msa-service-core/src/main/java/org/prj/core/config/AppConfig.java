package org.prj.core.config;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import static org.modelmapper.convention.MatchingStrategies.*;

@Configuration
public class AppConfig {
    @Value("${model.mapper.strategy:STRICT}")
    private String matchingStrategy;
    @Bean
    ModelMapper modelMapper(){
        ModelMapper modelMapper = new ModelMapper();
        switch (matchingStrategy.toUpperCase()) {
            case "STRICT":
                modelMapper.getConfiguration().setMatchingStrategy(STRICT);
                break;
            case "LOOSE":
                modelMapper.getConfiguration().setMatchingStrategy(LOOSE);
                break;
            default:
                modelMapper.getConfiguration().setMatchingStrategy(STANDARD);
                break;
        }

        return modelMapper;
    }
}
