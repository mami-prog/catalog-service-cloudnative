package catalogservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan //loads config data beans in the SPring context
public class CatalogServiceApp{

    public static void main(String[] args) {
        SpringApplication.run(CatalogServiceApp.class, args);
    }

}
