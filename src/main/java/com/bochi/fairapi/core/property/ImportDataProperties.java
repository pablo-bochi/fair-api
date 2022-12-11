package com.bochi.fairapi.core.property;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Data
@Component
@ConfigurationProperties(prefix = "import-data")
public class ImportDataProperties {

    private String filePath;

}
