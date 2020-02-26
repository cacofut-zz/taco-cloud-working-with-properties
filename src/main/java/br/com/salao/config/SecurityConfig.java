package br.com.salao.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private DataSource datasource;
	
	@Autowired
	private UserDetailsService userDetailsService;

	/*
	 * as definições devem ocorrer de cima para baixo
	 * o mais específico em cima e o mais geral em baixo
	 * */
	@Override
	public void configure(HttpSecurity http) throws Exception{
		/*
		http
			.authorizeRequests()
				.antMatchers("/design", "/orders").hasAnyRole("USER", "ADMIN", "MANAGER")
				.antMatchers("/", "/**", "/console").permitAll()// permit public access to home page							
			.and()
			.formLogin();
		*/
		
		/*
		http
			.authorizeRequests()
			.antMatchers("/design", "/orders")
				.access("hasRole('USER')")
			.antMatchers("/", "/**").access("permitAll")
			.and()
			.formLogin();
		*/
		
		/*http
			.authorizeRequests()
			.antMatchers("/design", "/orders")
				.access("hasRole('USER') && "
						+ "T(java.util.Calendar).getInstance().get("
						+ "T(java.util.Calendar).DAY_OF_WEEK) == "
						+ "T(java.util.Calendar).WEDNESDAY" )
			.antMatchers("/", "/**").access("permitAll")
			.and()
			.formLogin();
		*/
		
		http
			.authorizeRequests()
				.antMatchers("/design", "/orders")
				.access("hasRole('USER')")  
				.antMatchers("/", "/**").access("permitAll")
			.and()
				.formLogin()
					.loginPage("/login")
					.failureUrl("/login?error=yes")
					//.loginProcessingUrl("/authenticate") define o caminho para validar
					//.usernameParameter("user") define o nome do campo igual do formulário
					//.passwordParameter("pwd") define o nome do campo igual do formulário
					.defaultSuccessUrl("/design") // define uma página default quando o login for válido
					//.defaultSuccessUrl("", true) // força o direcionamento para a página default após o login
			.and()
				.logout()
					.logoutSuccessUrl("/login?logout")
			;
		
		http.csrf().disable();
		http.headers().frameOptions().disable();
	}
	
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		/*auth.inMemoryAuthentication()
			.withUser("caco")
				.password("{noop}123456")
				.authorities("ROLE_USER")
			.and()
			.withUser("lucas")
				.password("{noop}123456")
				.authorities("ROLE_USER");*/
		
		String users_query = 
			"SELECT USERNAME, PASSWORD, ENABLED FROM USER U\r\n" + 
			" WHERE U.USERNAME = ?";
		
		String authoryties_query = 
			"SELECT \r\n" + 
			"   U.USERNAME USERNAME,  \r\n" + 
			"   A.NAME     AUTHORITY\r\n" + 
			"  FROM USER U, AUTHORITY A, USER_AUTHORITIES UA\r\n" + 
			" WHERE U.USERNAME = ?\r\n" + 
			"   AND U.ID = UA.USER_ID\r\n" + 
			"   AND A.ID = UA.AUTHORITIES_ID";
		
		String groups_query = 
			"SELECT \r\n" + 
			"  G.ID ID,\r\n" + 
			"  G.NAME GROUP_NAME,\r\n" + 
			"  A.NAME AUTHORITY\r\n" + 
			"FROM \r\n" + 
			"  USER U,\r\n" + 
			"  GROUPS G,\r\n" + 
			"  AUTHORITY A,\r\n" + 
			"  GROUPS_USERS GU,\r\n" + 
			"  GROUPS_AUTHORITIES GA\r\n" + 
			"WHERE U.USERNAME = ?\r\n" + 
			"  AND U.ID = GU.USERS_ID\r\n" + 
			"  AND G.ID = GU.GROUPS_ID\r\n" + 
			"  AND G.ID = GA.GROUP_ID\r\n" + 
			"  AND A.ID = GA.AUTHORITIES_ID";
		
		auth.jdbcAuthentication()
			.dataSource(datasource)
			.usersByUsernameQuery(users_query)
			.authoritiesByUsernameQuery(authoryties_query)
			.groupAuthoritiesByUsername(groups_query);
		
		//auth.userDetailsService(userDetailsService);
		
				
	}
	
	@Bean
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
	
			
}
