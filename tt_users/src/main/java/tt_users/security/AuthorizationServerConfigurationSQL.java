package tt_users.security;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.approval.UserApprovalHandler;
import org.springframework.security.oauth2.provider.token.TokenStore;

@Configuration
@EnableAuthorizationServer
@PropertySource(value = { "classpath:app.properties" })
public class AuthorizationServerConfigurationSQL extends AuthorizationServerConfigurerAdapter{

	
	 private static String REALM = "MY_OAUTH_REALM";
     
	    @Autowired
	    private TokenStore tokenStore;
	 
	    @Autowired
	    private UserApprovalHandler userApprovalHandler;
	 
	    @Autowired
	    @Qualifier("authenticationManagerBean")
	    private AuthenticationManager authenticationManager;

		@Resource
	    private Environment environment;
	 

	    @Override
	    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
	 
	    	/*
	        clients.inMemory()
	            .withClient("my-trusted-client")
	            .authorizedGrantTypes("password", "authorization_code", "refresh_token", "implicit")
	            .authorities("ROLE_CLIENT", "ROLE_TRUSTED_CLIENT")
	            .scopes("read", "write", "trust")
	            .secret("secret")
	            .accessTokenValiditySeconds(120).//Access token is only valid for 2 minutes.
	            refreshTokenValiditySeconds(600);//Refresh token is only valid for 10 minutes.
	            
	            */
	    	
	    	clients.jdbc(dataSource())
			    	.withClient("my-trusted-client")
			    	.secret("secret")
		            .authorizedGrantTypes("password", "authorization_code", "refresh_token", "implicit")
		            .authorities("ROLE_CLIENT", "ROLE_TRUSTED_CLIENT")
		            .scopes("read", "write", "trust")
		            .accessTokenValiditySeconds(120).//Access token is only valid for 2 minutes.
		            refreshTokenValiditySeconds(600);//Refresh token is only valid for 10 minutes.;
	    }
	 
	    
	    @Bean
	    public DataSource dataSource() {
	        DriverManagerDataSource dataSource = new DriverManagerDataSource();
	        dataSource.setDriverClassName(environment.getRequiredProperty("db.driver"));
	        dataSource.setUrl(environment.getRequiredProperty("db.url"));
	        dataSource.setUsername(environment.getRequiredProperty("db.username"));
	        dataSource.setPassword(environment.getRequiredProperty("db.password"));
	        
	        return dataSource;
	    }

	    @Override
	    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
	        endpoints
	        	.tokenStore(tokenStore)
	        	.userApprovalHandler(userApprovalHandler)
	            .authenticationManager(authenticationManager);
	    }
	 
	    @Override
	    public void configure(AuthorizationServerSecurityConfigurer oauthServer) throws Exception {
	        oauthServer.realm(REALM+"/client");
	    }

}
