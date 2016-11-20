package com.mndeveci.spring.boot.rest.model;

import org.hibernate.envers.Audited;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Audited
public class City {

    @Id
    private Integer cityCode;

    private String name;

    public Integer getCityCode() {
        return cityCode;
    }

    public void setCityCode(Integer cityCode) {
        this.cityCode = cityCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        City city = (City) o;

        if (cityCode != null ? !cityCode.equals(city.cityCode) : city.cityCode != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return cityCode != null ? cityCode.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "City{" +
                "cityCode=" + cityCode +
                ", name='" + name + '\'' +
                '}';
    }
}
