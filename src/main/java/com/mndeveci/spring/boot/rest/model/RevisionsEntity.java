package com.mndeveci.spring.boot.rest.model;

import org.hibernate.envers.RevisionEntity;
import org.hibernate.envers.RevisionNumber;
import org.hibernate.envers.RevisionTimestamp;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;

/**
 * Created by mndeveci on 07.08.2016.
 */

@Entity
@RevisionEntity
public class RevisionsEntity {

    @Id
    @GeneratedValue
    @RevisionNumber
    private Long revisionId;

    @RevisionTimestamp
    private Date revisionDate;

}
