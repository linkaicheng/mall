package com.mall.test;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SshTest {
	private ApplicationContext ctx = null;
	{
		ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
	}

	@Test
	public void test() {
		DataSource dataSource = ctx.getBean(DataSource.class);
		System.out.println(dataSource);

	}

}
