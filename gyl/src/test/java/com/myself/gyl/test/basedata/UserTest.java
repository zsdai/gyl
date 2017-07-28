package com.myself.gyl.test.basedata;

import org.junit.Test;

import com.myself.gyl.basedata.service.UserService;
import com.myself.gyl.domain.basedata.User;
import com.myself.gyl.utils.SpringUtils;

public class UserTest extends SpringUtils{
	@Test
	public void testSave(){
		UserService userService=(UserService)context.getBean("userService");
		for (int i = 0; i < 10; i++) {
			User t = new User();
			
			t.setUsername("user"+i);
			t.setSex("男");
			t.setPhone("15826341148");
			t.setEmail("d"+i+"@163.com");
			userService.saveEntry(t);
		}
	}
}
