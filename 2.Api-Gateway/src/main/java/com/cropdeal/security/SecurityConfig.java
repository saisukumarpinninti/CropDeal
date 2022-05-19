package com.cropdeal.security;

import com.cropdeal.filters.JwtRequestFilter;
import com.cropdeal.service.MyUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyUserDetailsService myUserDetailsService;


    @Autowired
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailsService);
    }

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity.csrf().disable()
                .authorizeRequests()
                //For the Admin
                .antMatchers("/user/all","/api/farmer/farmer/all",
                        "/api/dealer/Dealer/all","/api/Admin/Dealer/all","/api/Admin/Admin/","api/crop/Crop/delete").hasRole("Admin")
                //For the Farmer & Admin
                .antMatchers("/api/farmer/farmer/update",
                        "/api/farmer/farmer/delete","api/crop/Crop/add","api/crop/Crop/update").hasAnyRole("Farmer","Admin")
                // For the Dealer & Admin
                .antMatchers("/api/dealer/dealer/update",
                        "/api/dealer/Dealer/delete").hasAnyRole("Dealer","Admin")
                // Open For All
                .antMatchers("/user/authenticate","/user/add","/user/update",
                        "/user/delete", "/api/farmer/farmer/add", "/api/dealer/Dealer/delete","/api/dealer/Dealer/delete","api/crop/Crop/true/all").permitAll()
                .anyRequest().authenticated().and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
        httpSecurity.addFilterBefore(jwtRequestFilter,UsernamePasswordAuthenticationFilter.class);
    }

}