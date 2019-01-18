package com.spaceRangers.config.security;

import com.spaceRangers.config.security.filters.GoogleOauthFilter;
import com.spaceRangers.config.security.service.RestEntriyPoint;
import com.spaceRangers.config.security.handlers.SuccessLoginHandler;
import com.spaceRangers.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.*;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.filter.OAuth2ClientContextFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.preauth.AbstractPreAuthenticatedProcessingFilter;
import org.springframework.security.web.authentication.rememberme.JdbcTokenRepositoryImpl;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.security.web.csrf.CsrfFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.filter.RequestContextFilter;

import javax.sql.DataSource;
import java.util.Arrays;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("userDetailsService")
    UserDetailsService userDetailsService;

    @Autowired
    RegistrationService registrationService;


    @Autowired
    public DataSource dataSource;

    @Autowired
    @Qualifier("googleRestTemplate")
    public OAuth2RestTemplate googleRestTemplate;

    @Autowired
    RestEntriyPoint restEntriyPoint;

    @Autowired
    SuccessLoginHandler successLoginHandler;


    @Autowired
    public void configureGlobalSecurity(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .authenticationProvider(authenticationProvider());
    }

    @Bean
    public GoogleOauthFilter googleOauthFilter(){
        GoogleOauthFilter googleOauthFilter = new GoogleOauthFilter("/login/google");
        googleOauthFilter.setRestTemplate(googleRestTemplate);
        googleOauthFilter.setRegistrationService(registrationService);

        return googleOauthFilter;
    }



    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
                .addFilterAfter(new OAuth2ClientContextFilter(), AbstractPreAuthenticatedProcessingFilter.class)
                .addFilterAfter(googleOauthFilter(), OAuth2ClientContextFilter.class)
                    .httpBasic()
                    .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login/google"));


        http
                .addFilterAfter(new RequestContextFilter(), CsrfFilter.class)
                .httpBasic()
                .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/login/google"));

        http
                .addFilterBefore(new CorsFilter(corsConfigurationSource()), UsernamePasswordAuthenticationFilter.class)
                .httpBasic()
                .authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/**"));


        http
                .authorizeRequests()
                    .antMatchers("/login/**").permitAll()
                .and()
                .csrf().disable()
                .httpBasic()
                .and()
                .formLogin()
                    .successHandler(successLoginHandler)
                    .failureHandler(failureHandler())
                .and()
                .exceptionHandling()
                    .authenticationEntryPoint(restEntriyPoint)
                .and()
                .authenticationProvider(authenticationProvider())

                .rememberMe()
                .rememberMeCookieName("remember-me-token")
                .userDetailsService(userDetailsService)
                .tokenRepository(persistentTokenRepository())
                .key("test")
                .tokenValiditySeconds(60*60*4)
                .alwaysRemember(true);
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Bean
    public DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());

        return authenticationProvider;
    }

    @Bean
    public PersistentTokenRepository persistentTokenRepository(){
        JdbcTokenRepositoryImpl jdbcTokenRepository = new JdbcTokenRepositoryImpl();
        jdbcTokenRepository.setDataSource(dataSource);
        return jdbcTokenRepository;
    }

    @Bean
    public AuthenticationFailureHandler failureHandler(){
        return new SimpleUrlAuthenticationFailureHandler();
    }


    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        final CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("HEAD",
                "GET", "POST", "PUT", "DELETE", "PATCH"));
        configuration.setAllowCredentials(true);
        configuration.setAllowedHeaders(Arrays.asList("*"));
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }

}

