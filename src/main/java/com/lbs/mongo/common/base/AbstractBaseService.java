package com.lbs.mongo.common.base;

import java.lang.reflect.ParameterizedType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.document.mongodb.MongoTemplate;

import com.lbs.mongo.common.dao.DatastoreMongo;

public abstract class AbstractBaseService<T> {

	@Autowired protected MongoTemplate mongoTemplate;
	
	@Autowired protected DatastoreMongo mds;
	
	@SuppressWarnings("unchecked")
	final protected Logger log = LoggerFactory.getLogger(((Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0]));


}
