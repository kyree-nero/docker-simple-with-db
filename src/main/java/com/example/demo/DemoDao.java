package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcOperations;
import org.springframework.stereotype.Component;

@Component
public class DemoDao {
	@Autowired  NamedParameterJdbcOperations jdbcOperations;
	
	public Integer samplerRead() {
		return jdbcOperations.queryForObject("SELECT count(*) FROM SAMPLE", new MapSqlParameterSource(), Integer.class);
	}
}
