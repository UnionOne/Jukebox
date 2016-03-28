package com.itibo.grob.services.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Persistable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;

@Service
@Transactional
public abstract class AbstractGenericService<T extends Persistable<ID>, ID extends Serializable,
        REPO extends JpaRepository<T, ID>> implements GenericService<T, ID> {

    private static final Logger LOGGER = LoggerFactory.getLogger(AbstractGenericService.class);

    @SuppressWarnings({"unchecked"})
    protected final String simpleTypeName = ((Class<T>) ((ParameterizedType) getClass().getGenericSuperclass())
            .getActualTypeArguments()[0]).getSimpleName();

    @Autowired
    protected REPO repository;

    @Override
    public long count() {
        return repository.count();
    }

    @Override
    public void delete(ID id) {
        LOGGER.info("Deleting {} with id {}", simpleTypeName, id);
        System.out.println("Deleting " + simpleTypeName + " entity with id = " + id);
        repository.delete(id);
    }

    @Override
    public void delete(Iterable<? extends T> entities) {
        LOGGER.info("Deleting {} sequence", simpleTypeName);
        System.out.println("Deleting " + simpleTypeName + " sequence");
        repository.delete(entities);
    }

    @Override
    public void delete(T entity) {
        LOGGER.info("Deleting {}", entity);
        System.out.println("Deleting " + entity);
        repository.delete(entity);
    }

    @Override
    public void deleteAll() {
        LOGGER.info("Deleting all {} entities", simpleTypeName);
        System.out.println("Deleting all " + simpleTypeName + " entities");
        repository.deleteAll();
    }

    @Override
    public void deleteAllInBatch() {
        LOGGER.info("Deleting all {} entities in batch", simpleTypeName);
        System.out.println("Deleting all " + simpleTypeName + " entities in batch");
        repository.deleteAllInBatch();
    }

    @Override
    public void deleteInBatch(Iterable<T> entities) {
        LOGGER.info("Deleting all {} entities in batch", simpleTypeName);
        System.out.println("Deleting all " + simpleTypeName + " entities in batch");
        repository.deleteInBatch(entities);
    }

    @Override
    public boolean exists(ID id) {
        return repository.exists(id);
    }

    @Override
    public List<T> findAll() {
        LOGGER.debug("Finding all {} entities", simpleTypeName);
        System.out.println("Finding all  " + simpleTypeName + " entities");
        List<T> found = repository.findAll();
        LOGGER.trace("Search results: {}", found);
        System.out.println("Search results: " + found);

        return found;
    }

    @Override
    public Iterable<T> findAll(Iterable<ID> ids) {
        LOGGER.debug("Finding all {} entities with ids {}", simpleTypeName, ids);
        System.out.println("Finding all  " + simpleTypeName + " entities with ids " + ids);
        Iterable<T> found = repository.findAll(ids);
        LOGGER.trace("Search results: {}", found);
        System.out.println("Search results: " + found);

        return found;
    }

    @Override
    public Page<T> findAll(Pageable pageable) {
        LOGGER.debug("Finding all {} entities with pageable restrictions {}", simpleTypeName, pageable);
        System.out.println("Finding all  " + simpleTypeName + " entities with pageable restrictions " + pageable);
        Page<T> found = repository.findAll(pageable);
        LOGGER.trace("Search results: {}", found);
        System.out.println("Search results: " + found);

        return found;
    }

    @Override
    public List<T> findAll(Sort sort) {
        LOGGER.debug("Finding all {} entities sorted by {}", simpleTypeName, sort);
        System.out.println("Finding all  " + simpleTypeName + " entities sorted by " + sort);
        List<T> found = repository.findAll(sort);
        LOGGER.trace("Search results: {}", found);
        System.out.println("Search results: " + found);

        return found;
    }

    @Override
    public T findOne(ID id) {
        LOGGER.debug("Finding all {} entities with id {}", simpleTypeName, id);
        System.out.println("Finding all  " + simpleTypeName + " entities with id " + id);
        T found = repository.findOne(id);
        LOGGER.trace("Search results: {}", found);
        System.out.println("Search results: " + found);

        return found;
    }

    @Override
    public void flush() {
        LOGGER.debug("Flushing all pending changes to the database");
        System.out.println("Flushing all pending changes to the database");
        repository.flush();
    }

    @Override
    public <S extends T> List<S> save(Iterable<S> entities) {
        LOGGER.info("Saving {} sequence into database", simpleTypeName);
        System.out.println("Saving " + simpleTypeName + "sequence into database");
        List<S> saved = repository.save(entities);
        LOGGER.debug("Successfully saved: {}", saved);
        System.out.println("Successfully saved: " + saved);

        return saved;
    }

    @Override
    public <S extends T> S save(S entity) {
        LOGGER.info("Saving {} into database", entity);
        System.out.println("Saving " + entity + " into database");
        S saved = repository.save(entity);
        LOGGER.debug("Successfully saved: {}", saved);
        System.out.println("Successfully saved: " + saved);

        return saved;
    }

    @Override
    public T saveAndFlush(T entity) {
        LOGGER.info("Saving {} into database and flushing", entity);
        System.out.println("Saving " + entity + " into database and flushing");
        T saved = repository.saveAndFlush(entity);
        LOGGER.debug("Successfully saved: {}", saved);
        System.out.println("Successfully saved: " + saved);

        return saved;
    }
}
