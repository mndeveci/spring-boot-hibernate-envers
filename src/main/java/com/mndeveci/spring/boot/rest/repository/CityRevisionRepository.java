package com.mndeveci.spring.boot.rest.repository;

import com.mndeveci.spring.boot.rest.model.City;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mndeveci on 18.11.2016.
 */

@Repository
@Transactional
public class CityRevisionRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    private CityRepository cityRepository;

    public List<City> listCityRevisions(Integer cityCode) {
        AuditReader auditReader = AuditReaderFactory.get(entityManager);
        City cityObject = cityRepository.findOne(cityCode);

        List<Number> revisions = auditReader.getRevisions(City.class, cityCode);

        List<City> cityRevisions = new ArrayList<>();
        for (Number revision : revisions) {
            cityRevisions.add(auditReader.find(City.class, cityCode, revision));
        }

        return cityRevisions;
    }
}
