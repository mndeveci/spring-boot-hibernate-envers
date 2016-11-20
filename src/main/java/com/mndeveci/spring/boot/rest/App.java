package com.mndeveci.spring.boot.rest;

import com.mndeveci.spring.boot.rest.model.City;
import com.mndeveci.spring.boot.rest.repository.CityRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class App {

    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }

    @Bean
    public CommandLineRunner demo(final CityRepository cityRepository) {
        return s -> {

            List<City> cities = new ArrayList<>();

            City cityInfo = new City();
            cityInfo.setCityCode(34);
            cityInfo.setName("Istanbul");
            cities.add(cityInfo);

            cityInfo = new City();
            cityInfo.setCityCode(6);
            cityInfo.setName("Ankara");
            cities.add(cityInfo);

            cityInfo = new City();
            cityInfo.setCityCode(35);
            cityInfo.setName("Izmir");
            cities.add(cityInfo);

            cityRepository.save(cities);
        };
    }

}
