package com.mndeveci.spring.boot.rest.controller;

import com.mndeveci.spring.boot.rest.model.City;
import com.mndeveci.spring.boot.rest.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class WelcomeController {

    @Autowired
    CityRepository cityRepository;

    @RequestMapping("/hello")
    public String hello() {
        return "Hello...";
    }

    @RequestMapping("/cities")
    public Iterable<City> getCities() {
        return cityRepository.findAll();
    }

}
