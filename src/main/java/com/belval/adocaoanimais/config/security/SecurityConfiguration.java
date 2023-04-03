package com.belval.adocaoanimais.config.security;

 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.session.HttpSessionEventPublisher;
import org.springframework.security.web.session.SimpleRedirectSessionInformationExpiredStrategy;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.belval.adocaoanimais.repository.UsuarioRepository;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
//@EnableWebSecurity(debug=true)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	@Bean
	public static BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Autowired
	private SSUserDetailsService userDetailsService;

	@Autowired
	private UsuarioRepository userRepository;

	@Override
    public UserDetailsService userDetailsServiceBean() throws Exception{
        return new SSUserDetailsService(userRepository);
    }

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		
		
		.sessionManagement()
        .invalidSessionUrl("/login?expired")
        .sessionFixation().migrateSession()
        .sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
        .maximumSessions(1)
        .expiredSessionStrategy(expiredSessionStrategy())
        .maxSessionsPreventsLogin(false)
        .and()
    .and()
		
		.authorizeRequests().antMatchers("/","/h2-console/**","/assets/**","/registro","/pet/public/galeria").permitAll()
				.and().formLogin().loginPage("/pet/login").permitAll()
				 .and()
		            .exceptionHandling()
		                .accessDeniedHandler((request, response,accessDeniedException) -> {
		                     response.sendRedirect("/?error=403");
		                })
				.and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/pet/logout"))
				.logoutSuccessUrl("/pet/login").permitAll().and().httpBasic();

		http.csrf().disable();
		http.headers().frameOptions().disable();
	}
	
    @Bean
    public HttpSessionEventPublisher httpSessionEventPublisher() {
        return new HttpSessionEventPublisher();
    }

    @Bean
    public ServletListenerRegistrationBean<HttpSessionEventPublisher> httpSessionEventPublisherListener() {
        return new ServletListenerRegistrationBean<>(new HttpSessionEventPublisher());
    }
	
    
    private SimpleRedirectSessionInformationExpiredStrategy expiredSessionStrategy() {
        return new SimpleRedirectSessionInformationExpiredStrategy("/pet/login?expired");
    }
//    
	@Bean
    public ServletContextInitializer servletContextInitializer() {
        return servletContext -> servletContext.getSessionCookieConfig().setMaxAge(60 * 5);
    }

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsServiceBean()).passwordEncoder(passwordEncoder());
	}

}
