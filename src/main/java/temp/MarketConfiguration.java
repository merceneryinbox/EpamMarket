package temp;

import dao.GoodDAO;
import dao.PostgresGoodDAO;
import dao.PostgresUserDAO;
import dao.UserDAO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class of Spring IoC container
 */
@Configuration
public class MarketConfiguration {
    @Bean
    GoodDAO goodDAO(){
      return new PostgresGoodDAO();
    }
    @Bean
    UserDAO userDAO(){
      return new PostgresUserDAO();
    }
}
