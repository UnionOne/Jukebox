package com.itibo.grob.services.impl;

import com.itibo.grob.dataaccess.model.Genre;
import com.itibo.grob.dataaccess.repository.GenreRepository;
import com.itibo.grob.services.GenreService;
import com.itibo.grob.services.common.AbstractGenericService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GenreServiceImpl extends AbstractGenericService<Genre, Integer, GenreRepository>
        implements GenreService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AccountServiceImpl.class);

    @Override
    public List<Genre> findAllByNameIgnoreCase(String name) {
        LOGGER.info("Finding {} entity with name = {}", simpleTypeName, name);
        return repository.findAllByNameIgnoreCase(name);
    }
}
