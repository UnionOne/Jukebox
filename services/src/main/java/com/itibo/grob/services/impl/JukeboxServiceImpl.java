package com.itibo.grob.services.impl;

import com.itibo.grob.dataaccess.model.Jukebox;
import com.itibo.grob.dataaccess.repository.JukeboxRepository;
import com.itibo.grob.services.JukeboxService;
import com.itibo.grob.services.common.AbstractGenericService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class JukeboxServiceImpl extends AbstractGenericService<Jukebox, Integer, JukeboxRepository>
        implements JukeboxService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountServiceImpl.class);
}
