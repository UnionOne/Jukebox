package com.itibo.grob.services.common;

import org.apache.commons.lang3.RandomUtils;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Persistable;
import org.springframework.data.domain.Sort;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.ArrayList;
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


}
