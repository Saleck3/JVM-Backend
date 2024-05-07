package com.jvm.lecti;

import com.jvm.lecti.controller.TestController;
import com.jvm.lecti.entity.TestEntity;
import com.jvm.lecti.service.TestService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class LectiApplicationTests {

	private TestController testController;
	private TestService testService;

	@Before
	public void init() {
		testService = mock(TestService.class);
		testController = new TestController(testService);
	}

	@Test
	public void testController(){
		Integer id = 1;
		TestEntity entity = new TestEntity(id);
		when(testService.getTest(id)).thenReturn(Optional.of(entity));
		assertEquals(testService.getTest(id).get().getId(), id);
	}

	}
