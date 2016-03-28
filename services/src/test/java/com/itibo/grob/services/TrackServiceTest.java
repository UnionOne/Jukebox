package com.itibo.grob.services;

import com.itibo.grob.dataaccess.model.Track;
import com.itibo.grob.services.common.GenericServiceTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Transactional
public class TrackServiceTest extends GenericServiceTest<Track, Integer, TrackService> {
    @Override
    protected Track generateEntity() {
        return entityUtils.generateTrack();
    }

    @Override
    protected Iterable<? extends Track> generateEntities(int maxEntitiesCount) {
        List<Track> list = new LinkedList<>();

        for (int i = 0; i < maxEntitiesCount; i++) {
            list.add(generateEntity());
        }

        return list;
    }
}
