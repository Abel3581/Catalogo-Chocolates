package com.chocolate.amaro.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.authentication.Http403ForbiddenEntryPoint;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
@Configuration
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JwtRequestFilters jwtRequestFilters;

    @Bean
    public  BCryptPasswordEncoder passwordEncoder (){return new BCryptPasswordEncoder();}

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder managerBuilder) throws Exception{
        managerBuilder.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.csrf().disable()
                .cors()
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST,"/auth/register").permitAll()
                .antMatchers(HttpMethod.POST,"/auth/login").permitAll()
                .antMatchers(HttpMethod.GET,"/product/{id}").permitAll()
                .antMatchers(HttpMethod.POST,"/category").permitAll()
                .antMatchers(HttpMethod.GET,"/category/all").permitAll()
                .antMatchers(HttpMethod.DELETE,"/category/{id}").permitAll()
                .antMatchers(HttpMethod.GET,"/product/product/{idCategory}").permitAll()
                .antMatchers(HttpMethod.PUT,"/product/{id}").permitAll()
                .antMatchers(HttpMethod.DELETE,"/product/{id}").permitAll()
                .antMatchers(HttpMethod.GET,"/product/page").permitAll()
                .antMatchers(HttpMethod.GET,"/product/all").permitAll()
                .antMatchers(HttpMethod.GET,"/product/name").permitAll()
                .antMatchers(HttpMethod.POST,"/product/save").permitAll()
                .antMatchers(HttpMethod.GET,"/auth/me").permitAll()
                .antMatchers(HttpMethod.PUT,"/user/{id}").permitAll()
                .antMatchers(HttpMethod.DELETE,"/user/{id}").permitAll()
                .antMatchers(HttpMethod.GET,"/user/all").permitAll()
                .antMatchers(HttpMethod.POST,"/trolley").permitAll()
                .antMatchers(HttpMethod.GET,"/trolley/{id}").permitAll()
                .antMatchers(HttpMethod.PUT,"/trolley/carts/{cartId}/products/{productId}").permitAll()
                .antMatchers(HttpMethod.DELETE,"/trolley/carts/{cartID}/products/{productID}").permitAll()
                .antMatchers(HttpMethod.GET,"/trolley/toBuy/cart/{id}").permitAll()
                .antMatchers(HttpMethod.POST,"/user/{cartId}").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .addFilterBefore(jwtRequestFilters, UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling()
                .authenticationEntryPoint(new Http403ForbiddenEntryPoint());
    }
}
