package com.itibo.grob.services.impl;

import com.itibo.grob.dataaccess.model.Role;
import com.itibo.grob.dataaccess.repository.RoleRepository;
import com.itibo.grob.services.RoleService;
import com.itibo.grob.services.common.AbstractGenericService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RoleServiceImpl extends AbstractGenericService<Role, Integer, RoleRepository>
        implements RoleService{

    private static final Logger LOGGER = LoggerFactory.getLogger(RoleServiceImpl.class);
}
