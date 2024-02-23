package com.example.movie_ticket.config;

import com.example.movie_ticket.service.impl.MyUserDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private MyUserDetailService myUserDetailService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(myUserDetailService).passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                .antMatchers("/dashboard/purchased/**").hasRole("USER")
                .antMatchers("/dashboard/category/**",
                        "/dashboard/employee/**",
                        "/dashboard/booking/**",
                        "/dashboard/customer/**",
                        "/dashboard/movies/**",
                        "/dashboard/blog/**"
                        ).hasRole("ADMIN")
                .antMatchers("/dashboard","/showtime/check-out/**").hasAnyRole("ADMIN","USER")
                .anyRequest().permitAll()
                .and()
                .formLogin().loginPage("/login")
                .defaultSuccessUrl("/dashboard").permitAll();

        http.authorizeRequests().and().exceptionHandling().accessDeniedPage("/403");
    }

}
