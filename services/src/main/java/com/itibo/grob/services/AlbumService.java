package com.itibo.grob.services;

import com.itibo.grob.dataaccess.model.Album;
import com.itibo.grob.services.common.GenericService;
import org.springframework.stereotype.Component;

@Component
public interface AlbumService extends GenericService<Album, Integer> {
    Album findOneAlbumById(Integer id);
}
