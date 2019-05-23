package App.controllers;

import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

import App.utils.View.HideOptionalProperties;

@CrossOrigin(origins={"http://localhost:4200"})
@RestController
@RequestMapping("/test")
public class TestingQueryController {

	@Bean
    public JdbcTemplate jdbcTemplate(DataSource dataSource)
    {
        return new JdbcTemplate(dataSource);
    }
    
    @Autowired
    private JdbcTemplate JdbcTemplate;
    
    @PersistenceContext protected EntityManager entityManager;
    
    @JsonView(HideOptionalProperties.class)
    @RequestMapping(value="/sql", method=RequestMethod.POST)
    public String nativeSql(@RequestBody String sql) {
    	try {
    		String result = (String)JdbcTemplate.queryForObject(sql, Object.class);
			return result;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}	
    }
    
    @JsonView(HideOptionalProperties.class)
    @RequestMapping(value="/jpql", method=RequestMethod.POST)
    public Collection<?> JPQL(@RequestBody String sql) {
    	try {
        	Query query = entityManager.createQuery(sql);
    		Collection<?> result = query.getResultList();
			return result;
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}	
    }
    
}
