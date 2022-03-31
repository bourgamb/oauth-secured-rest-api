package com.bourg.secured.oauthsecuredrestapi.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@Profile("opaque")
public class OpaqueSecurityConfig extends WebSecurityConfigurerAdapter {

	@Value("${spring.security.oauth2.resourceserver.opaque.introspection-uri}")
    String introspectionUri;

    @Value("${spring.security.oauth2.resourceserver.opaque.introspection-client-id}")
    String clientId;

    @Value("${spring.security.oauth2.resourceserver.opaque.introspection-client-secret}")
    String clientSecret;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
          .authorizeRequests(authz -> authz
            .antMatchers(HttpMethod.GET, "/user/**").hasAuthority("SCOPE_email")
            //.antMatchers(HttpMethod.POST, "/bars").hasAuthority("SCOPE_write")
            .anyRequest().authenticated())
          .oauth2ResourceServer(oauth2 -> oauth2
            .opaqueToken(token -> token.introspectionUri(this.introspectionUri)
              .introspectionClientCredentials(this.clientId, this.clientSecret)));
    }
	
}
