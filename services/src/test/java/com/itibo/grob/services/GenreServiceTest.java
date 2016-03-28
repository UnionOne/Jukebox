package com.itibo.grob.services;

import com.itibo.grob.dataaccess.model.Genre;
import com.itibo.grob.services.common.EntityUtils;
import com.itibo.grob.services.common.GenericServiceTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Transactional
public class GenreServiceTest extends GenericServiceTest<Genre, Integer, GenreService> {
    @Override
    protected Genre generateEntity() {
        return entityUtils.generateGenre();
    }

    @Override
    protected Iterable<? extends Genre> generateEntities(int maxEntitiesCount) {
        List<Genre> list = new LinkedList<>();

        for (int i = 0; i < maxEntitiesCount; i++) {
            list.add(generateEntity());
        }

        return list;
    }

//    @Test
//    public void findAllByNameIgnoreCaseTest() {
//        List<Genre> genres = new LinkedList<>();
//
//        for(int i = 0; i < EntityUtils.getRandomInteger(EntityUtils.MAX_ENTITIES_COUNT); i++) {
//            genres.add(entityUtils.generateGenre());
//        }
//
//        List<Genre> saved = service.save(genres);
//        List<Genre> found = service.findAllByNameIgnoreCase(entity.getName());
//
//        Assert.assertEquals(saved, found);
//
//
//    }
}
