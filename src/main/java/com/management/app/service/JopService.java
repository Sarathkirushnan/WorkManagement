package com.management.app.service;

import com.management.app.entitys.Jop;

public interface JopService {

	boolean existByName(String name);

	Jop saveJop(Jop jop);

}
