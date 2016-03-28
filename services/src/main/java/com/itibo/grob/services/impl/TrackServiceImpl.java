package com.itibo.grob.services.impl;

import com.itibo.grob.dataaccess.model.Track;
import com.itibo.grob.dataaccess.repository.TrackRepository;
import com.itibo.grob.services.TrackService;
import com.itibo.grob.services.common.AbstractGenericService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TrackServiceImpl extends AbstractGenericService<Track, Integer, TrackRepository>
        implements TrackService {

    private static final Logger LOGGER = LoggerFactory.getLogger(RoleServiceImpl.class);
}
