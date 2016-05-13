package com.itibo.grob.services;

import com.itibo.grob.dataaccess.model.Track;
import com.itibo.grob.services.common.GenericService;
import org.springframework.stereotype.Component;

@Component
public interface TrackService extends GenericService<Track, Integer> {

}
