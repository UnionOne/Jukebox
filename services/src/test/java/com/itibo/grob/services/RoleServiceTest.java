package com.itibo.grob.services;

import com.itibo.grob.dataaccess.model.Role;
import com.itibo.grob.services.common.GenericServiceTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;

@Transactional
public class RoleServiceTest extends GenericServiceTest<Role, Integer, RoleService> {
    @Override
    protected Role generateEntity() {
        return entityUtils.generateRole();
    }

    @Override
    protected Iterable<? extends Role> generateEntities(int maxEntitiesCount) {
        List<Role> list = new LinkedList<>();

        for(int i = 0; i < maxEntitiesCount; i++) {
            list.add(generateEntity());
        }

        return list;
    }
}
