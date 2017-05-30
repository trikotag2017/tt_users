package tt_users.config;

import java.util.Properties;

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate4.HibernateTransactionManager;
import org.springframework.orm.hibernate4.LocalSessionFactoryBuilder;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import tt_users.model.User;
import tt_users.model.UserRole;


@Configuration
@EnableTransactionManagement
@ComponentScan({ "tt_users" })
@PropertySource(value = { "classpath:app.properties" })
public class HibernateConfiguration {
	
	@Resource
    private Environment environment;
 
    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(environment.getRequiredProperty("db.driver"));
        dataSource.setUrl(environment.getRequiredProperty("db.url"));
        dataSource.setUsername(environment.getRequiredProperty("db.username"));
        dataSource.setPassword(environment.getRequiredProperty("db.password"));
        
        return dataSource;
    }
     
    private Properties hibernateProperties() {
        Properties properties = new Properties();
        properties.put("hibernate.dialect", environment.getRequiredProperty("db.hibernate.dialect"));
        properties.put("hibernate.show_sql", environment.getRequiredProperty("db.hibernate.show_sql"));
        properties.put("hibernate.format_sql", environment.getRequiredProperty("db.hibernate.format_sql"));
        properties.put("hibernate.jdbc.use_streams_for_binary", environment.getRequiredProperty("db.use_streams_for_binary"));
        return properties;        
    }

    @Autowired
    @Bean(name = "sessionFactory")
    public SessionFactory getSessionFactory(DataSource dataSource) {
     
        LocalSessionFactoryBuilder sessionBuilder = new LocalSessionFactoryBuilder(dataSource);
        sessionBuilder.addProperties(hibernateProperties());

        
        sessionBuilder.addAnnotatedClasses(User.class);
        sessionBuilder.addAnnotatedClasses(UserRole.class);
        return sessionBuilder.buildSessionFactory();
    }
    
    @Autowired
    @Bean(name = "transactionManager")
    public HibernateTransactionManager getTransactionManager() {
     
        return new HibernateTransactionManager(getSessionFactory(dataSource()));
    }
    

}
