package feast.config;

import feast.models.Chicken3;
import feast.models.Duck2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "feast")
public class AppConfig {

    @Bean
    public Duck2 duck2(Chicken3 chicken) {
        return new Duck2(chicken);
    }
}