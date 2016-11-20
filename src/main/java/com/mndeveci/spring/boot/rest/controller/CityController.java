package com.mndeveci.spring.boot.rest.controller;

import com.mndeveci.spring.boot.rest.model.City;
import com.mndeveci.spring.boot.rest.repository.CityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cities")
public class CityController {

    @Autowired
    private CityRepository cityRepository;

    @RequestMapping
    public Iterable<City> listAll() {
        return cityRepository.findAll();
    }

    @RequestMapping(value = "/{cityCode}", method = RequestMethod.GET)
    public City get(@PathVariable Integer cityCode) {
        return cityRepository.findOne(cityCode);
    }

    @RequestMapping(value = "/{cityCode}", method = RequestMethod.POST)
    public City save(@PathVariable Integer cityCode, @RequestBody City city) {
        return cityRepository.save(city);
    }

    @RequestMapping(value = "/{cityCode}", method = RequestMethod.PUT)
    public City update(@PathVariable Integer cityCode, @RequestBody City city) {
        City cityObject = cityRepository.findOne(cityCode);

        cityObject.setName(city.getName());

        return cityRepository.save(cityObject);
    }

    @RequestMapping(value = "/{cityCode}", method = RequestMethod.DELETE)
    public boolean delete(@PathVariable Integer cityCode) {
        cityRepository.delete(cityCode);
        return true;
    }


}
