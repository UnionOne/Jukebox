package com.itibo.grob.services;

import com.itibo.grob.dataaccess.model.Band;
import com.itibo.grob.services.common.GenericService;
import org.springframework.stereotype.Component;

@Component
public interface BandService extends GenericService<Band, Integer> {

}
