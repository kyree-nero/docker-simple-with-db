package com.example.demo;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment=SpringBootTest.WebEnvironment.MOCK)
@ActiveProfiles("test")
@TestPropertySource("/application-test.properties")
@Sql({
	"/META-INF/data/h2/drops.sql", 
	"/META-INF/data/h2/tables.sql", 
	"/META-INF/data/h2/inserts.sql"
})
@AutoConfigureMockMvc
public class DemoIT {
	@Autowired MockMvc mockMvc;
	@Autowired WebApplicationContext webApplicationContext;
	
	
	@Before public void before() {
		mockMvc = MockMvcBuilders
			.webAppContextSetup(webApplicationContext)
			.build();
	}
	
	@Test public void test()  throws Exception{
		MvcResult result = mockMvc.perform(
				MockMvcRequestBuilders.get("/content", new Object[] {})
				
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				
		)
				.andDo(MockMvcResultHandlers.print())
		.andExpect(MockMvcResultMatchers.status().isOk())
		//.andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
		.andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.notNullValue()))
		.andReturn();
	}
	
	@Test public void testJdbc()  throws Exception{
		MvcResult result = mockMvc.perform(
				MockMvcRequestBuilders.get("/sample-jdbc-read", new Object[] {})
				
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				
		)
				.andDo(MockMvcResultHandlers.print())
		.andExpect(MockMvcResultMatchers.status().isOk())
		//.andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
		.andExpect(MockMvcResultMatchers.jsonPath("$.id", Matchers.notNullValue()))
		.andExpect(MockMvcResultMatchers.jsonPath("$.text", Matchers.notNullValue()))
		.andReturn();
	}
}
