package br.com.placeservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.EnableR2dbcAuditing;

import br.com.placeservice.domain.PlaceRepository;
import br.com.placeservice.domain.PlaceService;

@Configuration
@EnableR2dbcAuditing // Para preencher o createdAt e updatedAt
public class PlaceServiceConfig {

    @Bean
    PlaceService placeService(PlaceRepository placeRepository) {
        return new PlaceService(placeRepository);
    }
}
