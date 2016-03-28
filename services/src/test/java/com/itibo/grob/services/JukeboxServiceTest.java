package com.itibo.grob.services;

import com.itibo.grob.dataaccess.model.Jukebox;
import com.itibo.grob.services.common.GenericServiceTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Transactional
public class JukeboxServiceTest extends GenericServiceTest<Jukebox, Integer, JukeboxService> {

    @Override
    protected Jukebox generateEntity() {
        return entityUtils.generateJukebox();
    }

    @Override
    protected Iterable<? extends Jukebox> generateEntities(int maxEntitiesCount) {
        List<Jukebox> list = new ArrayList<>();

        for (int i = 0; i < maxEntitiesCount; i++) {
            list.add(entityUtils.generateJukebox());
        }

        return list;
    }
}
