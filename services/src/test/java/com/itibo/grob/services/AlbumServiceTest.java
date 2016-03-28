package com.itibo.grob.services;

import com.itibo.grob.dataaccess.model.Album;
import com.itibo.grob.services.common.GenericServiceTest;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Transactional
public class AlbumServiceTest extends GenericServiceTest<Album, Integer, AlbumService> {
    @Override
    protected Album generateEntity() {
        return entityUtils.generateAlbum();
    }

    @Override
    protected Iterable<? extends Album> generateEntities(int maxEntitiesCount) {
        List<Album> list = new LinkedList<>();

        for (int i = 0; i < maxEntitiesCount; i++) {
            list.add(generateEntity());
        }

        return list;
    }

    @Test
    public void findAlbumByNameIgnoreCaseTest() {
        Album saved = service.save(entity);
        Album found = service.findAlbumByNameIgnoreCase(entity.getName());

        Assert.assertEquals(saved, found);
    }
}
