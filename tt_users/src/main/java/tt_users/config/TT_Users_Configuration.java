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
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

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

    
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/*.htm").addResourceLocations("/WEB-INF/jsp/");
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
    }

	@Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("forward:/index.jsp");
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        viewResolver.setPrefix("/WEB-INF/jsp/");
        viewResolver.setSuffix(".jsp");
        registry.viewResolver(viewResolver);
    }


}
