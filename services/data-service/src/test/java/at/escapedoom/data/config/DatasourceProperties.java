package at.escapedoom.data.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Data
@ConfigurationProperties(prefix = "spring.datasource")
public class DatasourceProperties {

    private String url;
    private String username;
    private String password;
    private String driverClassName;
}
