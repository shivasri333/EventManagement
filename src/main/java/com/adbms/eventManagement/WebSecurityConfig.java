package com.adbms.eventManagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.adbms.eventManagement.service.UserDetailService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
    private UserDetailService userDetailsService;
	
	@Override
    protected void configure(AuthenticationManagerBuilder auth)
            throws Exception {
        auth.authenticationProvider(authenticationProvider());
                
    }
	
	@Bean
	public UserDetailsService userDetailsService() {
		return new UserDetailService();
	}

	@Bean
	public AuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(userDetailsService());
		daoAuthenticationProvider.setPasswordEncoder(bCryptPasswordEncoder);
		return daoAuthenticationProvider;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
	    http.authorizeRequests(requests -> requests
	            .antMatchers("/").permitAll()
	            .antMatchers("/login").permitAll()
	            .antMatchers("/registration").permitAll()
	            .antMatchers("/admin/registration").permitAll()
	            .antMatchers("/admin/**").hasAuthority("ADMIN")
	            .antMatchers("/**").hasAnyAuthority("USER", "ADMIN")
	            .anyRequest().authenticated())
	            .formLogin(login -> login
	                    .loginPage("/login").permitAll().defaultSuccessUrl("/homePage", true)
	                    .usernameParameter("username")
	                    .passwordParameter("password").successHandler((request, response, authentication) -> {
	                        // Redirect user based on role
	                        for (GrantedAuthority authority : authentication.getAuthorities()) {
	                            if (authority.getAuthority().equals("ADMIN")) {
	                                response.sendRedirect("/adminHomePage");
	                            } else {
	                                response.sendRedirect("/homePage");
	                            }
	                        }
	                    })).logout(logout -> logout
	            .permitAll()
	            .logoutSuccessUrl("/").invalidateHttpSession(true)
	            .deleteCookies("JSESSIONID")).exceptionHandling(handling -> handling
	            .accessDeniedPage("/access-denied")).csrf(csrf -> csrf.disable());
	}
	
	@Override
    public void configure(WebSecurity web) throws Exception {
        web
                .ignoring()
                .antMatchers("/resources/**", "/static/**", "/css/**", "/js/**", "/images/**");
    }
	
	}
	
