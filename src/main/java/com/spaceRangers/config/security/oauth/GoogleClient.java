package com.spaceRangers.config.security.oauth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.grant.code.AuthorizationCodeResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationManager;

import java.util.Arrays;

@EnableOAuth2Client
@Configuration
public class GoogleClient {

    private String clientId = "1013114970254-hkpidf6s43v90dcvqfg6nfcqmi1sq9ho.apps.googleusercontent.com";
    private String clientSecret = "cNSxkGAPTMXTYxWKXtMPkuIU";
    private String accessTokenUri = "https://www.googleapis.com/oauth2/v3/token";
    private String userAuthorizationUri = "https://accounts.google.com/o/oauth2/auth";
    private String redirectUri = "http://localhost:8080/login/google";


    @Bean
    public OAuth2ProtectedResourceDetails googleResourceDetails(){
        AuthorizationCodeResourceDetails details = new AuthorizationCodeResourceDetails();
        details.setClientId(clientId);
        details.setClientSecret(clientSecret);
        details.setAccessTokenUri(accessTokenUri);
        details.setUserAuthorizationUri(userAuthorizationUri);
        details.setPreEstablishedRedirectUri(redirectUri);
        details.setScope(Arrays.asList("email"));
        details.setUseCurrentUri(false);
        return details;
    }

    @Bean
    public OAuth2RestTemplate googleRestTemplate(OAuth2ProtectedResourceDetails googleResourceDetails, OAuth2ClientContext context){
        OAuth2RestTemplate restTemplate = new OAuth2RestTemplate(googleResourceDetails, context);
        return restTemplate;
    }
}
