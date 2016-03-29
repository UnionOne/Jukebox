package com.itibo.grob.services.impl;

import com.itibo.grob.dataaccess.model.Album;
import com.itibo.grob.dataaccess.repository.AlbumRepository;
import com.itibo.grob.services.AlbumService;
import com.itibo.grob.services.common.AbstractGenericService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AlbumServiceImpl extends AbstractGenericService<Album, Integer, AlbumRepository>
        implements AlbumService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Override
    public Album findAlbumByNameIgnoreCase(String name) {
        LOGGER.info("Finding {} entity with name = {}", simpleTypeName, name);
        return repository.findAlbumByNameIgnoreCase(name);
    }
}
