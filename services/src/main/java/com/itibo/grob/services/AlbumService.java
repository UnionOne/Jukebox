package com.itibo.grob.services;

import com.itibo.grob.dataaccess.model.Album;
import com.itibo.grob.services.common.GenericService;

public interface AlbumService extends GenericService<Album, Integer> {
    Album findAlbumByNameIgnoreCase(String name);
}
