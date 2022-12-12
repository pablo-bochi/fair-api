package com.backend.fairapi.core.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "basic-credentials")
public class BasicCredentialsProperty {

    private String defaultUser;
    private String defaultPassword;

}
