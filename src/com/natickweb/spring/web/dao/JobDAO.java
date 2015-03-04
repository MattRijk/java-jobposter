package com.natickweb.spring.web.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSourceUtils;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component("jobsDao")
public class JobDAO {

	private NamedParameterJdbcTemplate jdbc;
	
	@Autowired
	public void setDataSource(DataSource jdbc) {
		this.jdbc = new NamedParameterJdbcTemplate(jdbc);
	}

	public List<Job> getJobs() {

		return jdbc.query("select * from Jobs", new RowMapper<Job>() {

			public Job mapRow(ResultSet rs, int rowNum) throws SQLException {
				Job job = new Job();

				job.setId(rs.getInt("id"));
				job.setName(rs.getString("name"));
				job.setText(rs.getString("text"));
				job.setEmail(rs.getString("email"));

				return job;
			}

		});
	}
	
	public boolean update(Job job) {
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(job);
		
		return jdbc.update("update Jobs set name=:name, text=:text, email=:email where id=:id", params) == 1;
	}
	
	public boolean create(Job job) {
		
		BeanPropertySqlParameterSource params = new BeanPropertySqlParameterSource(job);
		
		return jdbc.update("insert into Jobs (name, text, email) values (:name, :text, :email)", params) == 1;
	}
	
	@Transactional
	public int[] create(List<Job> jobs) {
		
		SqlParameterSource[] params = SqlParameterSourceUtils.createBatch(jobs.toArray());
		
		return jdbc.batchUpdate("insert into Jobs (id, name, text, email) values (:id, :name, :text, :email)", params);
	}
	
	public boolean delete(int id) {
		MapSqlParameterSource params = new MapSqlParameterSource("id", id);
		
		return jdbc.update("delete from Jobs where id=:id", params) == 1;
	}

	public Job getJob(int id) {

		MapSqlParameterSource params = new MapSqlParameterSource();
		params.addValue("id", id);

		return jdbc.queryForObject("select * from jobs where id=:id", params,
				new RowMapper<Job>() {

					public Job mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						Job job = new Job();

						job.setId(rs.getInt("id"));
						job.setName(rs.getString("name"));
						job.setText(rs.getString("text"));
					    job.setEmail(rs.getString("email"));

						return job;
					}

				});
	}
	
}
