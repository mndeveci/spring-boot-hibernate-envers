package com.mndeveci.spring.boot.rest.repository;

import com.mndeveci.spring.boot.rest.model.City;
import com.mndeveci.spring.boot.rest.model.EntityWithRevision;
import com.mndeveci.spring.boot.rest.model.RevisionsEntity;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.AuditReaderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
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

    public List<EntityWithRevision<City>> listCityRevisions(Integer cityCode) {
        AuditReader auditReader = AuditReaderFactory.get(entityManager);
        City cityObject = cityRepository.findOne(cityCode);

        List<Number> revisions = auditReader.getRevisions(City.class, cityCode);

        List<EntityWithRevision<City>> cityRevisions = new ArrayList<>();
        for (Number revision : revisions) {
            City cityRevision = auditReader.find(City.class, cityCode, revision);
            Date revisionDate = auditReader.getRevisionDate(revision);

            cityRevisions.add(
                    new EntityWithRevision(
                            new RevisionsEntity(revision.longValue(), revisionDate), cityRevision));
        }

        return cityRevisions;
    }
}
