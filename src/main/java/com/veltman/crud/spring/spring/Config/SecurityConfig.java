package com.veltman.crud.spring.spring.Config;

import com.veltman.crud.spring.spring.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private UserService userService;
    private SuccessUserHandler successUserHandler;

    @Autowired
    public void setUserService(UserService userService, SuccessUserHandler successUserHandler) {
        this.successUserHandler = successUserHandler;
        this.userService = userService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").authenticated()
                .antMatchers("/admin/**").hasRole("ADMIN")
                .antMatchers("/user/**").hasAnyRole("ADMIN", "USER", "BUH", "EXECUTIVE_DIRECTOR", "OWNER","HR")
                .antMatchers("/buh/**").hasAnyRole("BUH")
                .antMatchers("/hr/**").hasAnyRole("HR")
                .antMatchers("/executiveDirector/**").hasAnyRole("EXECUTIVE_DIRECTOR")
                .antMatchers("/owner/**").hasAnyRole("OWNER")


                .antMatchers("rest/**").permitAll() //хз
                .and()
                .csrf().disable() //хз
                .formLogin().successHandler(successUserHandler)
                .and()
                .logout().logoutSuccessUrl("/");
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        authenticationProvider.setUserDetailsService(userService);

        return authenticationProvider;
    }

}