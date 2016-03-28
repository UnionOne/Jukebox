package com.itibo.grob.services;

import com.itibo.grob.dataaccess.model.Band;
import com.itibo.grob.services.common.GenericServiceTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Transactional
public class BandServiceTest extends GenericServiceTest<Band, Integer, BandService> {
    @Override
    protected Band generateEntity() {
        return entityUtils.generateBand();
    }

    @Override
    protected Iterable<? extends Band> generateEntities(int maxEntitiesCount) {
        List<Band> list = new LinkedList<>();

        for (int i = 0; i < maxEntitiesCount; i++) {
            list.add(generateEntity());
        }

        return list;
    }
}
