package catalogservice.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jdbc.repository.config.EnableJdbcAuditing;

/*
    enable auditing
 */
@Configuration
@EnableJdbcAuditing
public class DataConfig {
}
