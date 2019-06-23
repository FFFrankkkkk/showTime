package com.showTime.entity;

import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(
	locations={
		"classpath:applicationContext-core.xml"
		,"classpath:applicationContext-jpa.xml"
})   
public class BaseTest {

}
