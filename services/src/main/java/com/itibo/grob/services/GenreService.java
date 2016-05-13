package com.itibo.grob.services;

import com.itibo.grob.dataaccess.model.Genre;
import com.itibo.grob.services.common.GenericService;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface GenreService extends GenericService<Genre, Integer> {
    List<Genre> findAllByNameIgnoreCase(String name);
}
