package com.lbs.mongo;

import junit.framework.TestCase;

import org.junit.runner.RunWith;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:app-config-test.xml" })
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class})
public abstract class BaseTest extends TestCase {
	
	final protected Logger log = LoggerFactory.getLogger("TEST");
}
