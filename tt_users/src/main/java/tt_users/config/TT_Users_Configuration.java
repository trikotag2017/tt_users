package tt_users.config;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages = "tt_users")
@PropertySource("classpath:app.properties")
public class TT_Users_Configuration   extends WebMvcConfigurerAdapter  {
	
	private static final String PROP_DATABASE_DRIVER = "db.driver";
    private static final String PROP_DATABASE_PASSWORD = "db.password";
    private static final String PROP_DATABASE_URL = "db.url";
    private static final String PROP_DATABASE_USERNAME = "db.username";

    
	@Resource
    private Environment env;

    
    @Bean(name = "dataSource")
    public DataSource dataSource() {
    	DriverManagerDataSource  dataSource = new DriverManagerDataSource ();
             
            dataSource.setDriverClassName(env.getRequiredProperty(PROP_DATABASE_DRIVER));
            dataSource.setUrl(env.getRequiredProperty(PROP_DATABASE_URL));
            dataSource.setUsername(env.getRequiredProperty(PROP_DATABASE_USERNAME));
            dataSource.setPassword(env.getRequiredProperty(PROP_DATABASE_PASSWORD));
            System.out.println("======== dataSource =======");
            return dataSource;
    }


}
