package com.itibo.grob.services.common;

import org.springframework.data.domain.Persistable;

import java.io.Serializable;

public abstract class GenericServiceTest<T extends Persistable<ID>, ID extends Serializable,
            SERVICE extends GenericService<T, ID>> extends AbstractSpringTest {


}
