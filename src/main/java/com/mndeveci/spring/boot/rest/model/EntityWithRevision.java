package com.mndeveci.spring.boot.rest.model;

/**
 * Created by mndeveci on 20.11.2016.
 */
public class EntityWithRevision <T> {

    private RevisionsEntity revision;

    private T entity;

    public EntityWithRevision(RevisionsEntity revision, T entity) {
        this.revision = revision;
        this.entity = entity;
    }

    public RevisionsEntity getRevision() {
        return revision;
    }

    public T getEntity() {
        return entity;
    }
}
