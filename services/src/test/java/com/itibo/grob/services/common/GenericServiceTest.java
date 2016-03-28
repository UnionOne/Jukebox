package com.itibo.grob.services.common;

import org.apache.commons.lang3.RandomUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Persistable;
import org.springframework.data.domain.Sort;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class GenericServiceTest<T extends Persistable<ID>, ID extends Serializable,
        SERVICE extends GenericService<T, ID>> extends AbstractSpringTest {

    protected T entity;
    protected Iterable<? extends T> entities;
    protected Sort sort;
    protected Pageable pageable;
    protected List<String> columnNames;

    @Autowired
    protected SERVICE service;

    @Autowired
    protected EntityUtils entityUtils;

    @Before
    public void init() {
        this.entity = generateEntity();
    }

    protected abstract T generateEntity();

    protected abstract Iterable<? extends T> generateEntities(int maxEntitiesCount);

    protected Sort generateSort() {
        return new Sort(getRandomFieldName());
    }

    protected Pageable generatePageable() {
        return new PageRequest(1, EntityUtils.getRandomInteger(EntityUtils.MAX_ENTITIES_COUNT));
    }

    protected List<String> getFieldsNames() {
        List<String> result = new ArrayList<>();

        for (Field field : entity.getClass().getDeclaredFields()) {
            if (field.getName().equals("serialVersionUID")) {
                continue;
            }
            result.add(field.getName());
        }
        return result;
    }

    protected String getRandomFieldName() {
        return getFieldsNames().get(RandomUtils.nextInt(0, getFieldsNames().size()));
    }

    @Test
    public void countEtitiesTest() {
        long before = service.count();
        service.save(entity);
        long after = service.count();
        Assert.assertEquals(before + 1, after);
    }

    @Test
    public void deleteEntityByIdTest() {
        ID saveId = service.save(entity).getId();
        service.delete(saveId);
        Assert.assertFalse(service.exists(saveId));
    }

    @Test
    public void deleteSequenceOfEntitiesTest() {
        Iterable<? extends T> saved = service.save(entities);
        List<ID> ids = new ArrayList<>();

        for (T t : saved) {
            ids.add(t.getId());
        }

        service.delete(saved);
        Assert.assertEquals(Collections.EMPTY_LIST, service.findAll(ids));
    }

    @Test
    public void deleteEntityTest() {
        T saved = service.save(entity);
        service.delete(saved);
        Assert.assertFalse(service.exists(saved.getId()));
    }

    @Test
    public void deleteAllEntitiesTest() {
        service.save(entity);
        service.deleteAll();
        Assert.assertTrue(service.count() == 0);
    }

    @Test
    public void deleteAllEntitiesInBatchTest() {
        service.save(entity);
        service.deleteAllInBatch();
        Assert.assertTrue(service.count() == 0);
    }

    @Test
    public void deleteSequenceOfEntitiesInBatchTest() {
        @SuppressWarnings("unchecked")
        Iterable<T> saved = (Iterable<T>) service.save(entities);
        List<ID> ids = new ArrayList<>();

        for (T t : saved) {
            ids.add(t.getId());
        }

        service.deleteInBatch(saved);
        Assert.assertEquals(Collections.EMPTY_LIST, service.findAll(ids));
    }

    @Test
    public void entityExistenceByIdTest() {
        T saved = service.save(entity);
        Assert.assertTrue(service.exists(saved.getId()));
    }

    @Test
    public void findAllEntitiesTest() {
        service.deleteAll();
        Iterable<? extends T> saved = service.save(entities);
        Iterable<T> found = service.findAll();
        Assert.assertEquals(saved, found);
    }

    @Test
    public void findAllEntitiesByIds() {
        Iterable<? extends T> saved = service.save(entities);
        List<ID> ids = new ArrayList<>();

        for (T t : saved) {
            ids.add(t.getId());
        }

        Iterable<T> found = service.findAll(ids);
        Assert.assertEquals(saved, found);
    }

    @Test
    public void findAllEntititesByPageableTest() {
        Iterable<T> found = service.findAll(sort);
        service.delete(found);
        Assert.assertEquals(Collections.EMPTY_LIST, found);
    }

    @Test
    public void findAllEntitiesBySortTest() {
        Iterable<T> found = service.findAll(sort);
        service.delete(found);
        Assert.assertEquals(Collections.EMPTY_LIST, service.findAll(sort));
    }

    @Test
    public void findOneEntityByIdTest() {
        service.save(entity);
        T found = service.findOne(entity.getId());
        Assert.assertEquals(found == null, !service.exists(entity.getId()));
    }

    @Test
    public void flushPendingChangesTest() {
        service.flush();
    }

    @Test
    public void saveSequenceOfEntitiesTest() {
        Iterable<? extends T> saved = service.save(entities);

        List<ID> ids = new ArrayList<>();

        for (T t : saved) {
            ids.add(t.getId());
        }

        Assert.assertEquals(saved, service.findAll(ids));
    }

    @Test
    public void saveOneEntityTest() {
        T saved = service.save(entity);
        Assert.assertEquals(entity, service.findOne(saved.getId()));
    }

    @Test
    public void saveOneEntityAndFlushTest() {
        T saved = service.save(entity);
        Assert.assertEquals(entity, service.findOne(saved.getId()));
    }
}
