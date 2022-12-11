package com.bochi.fairapi.importdata;

import com.bochi.fairapi.core.property.ImportDataProperties;
import com.bochi.fairapi.domain.Fair;
import com.bochi.fairapi.presentation.FairService;
import com.bochi.fairapi.helper.CsvHelper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

@Slf4j
@Component
@AllArgsConstructor
public class ImportCsvData implements CommandLineRunner {

    private final FairService fairService;
    private final ImportDataProperties importDataProperties;

    @Override
    public void run(String... args) {
        log.info("Started importing...");
        importAndSaveCsvData();
        log.info("Finished importing...");
        log.info("Application is ready to receive requests!");
    }

    private void importAndSaveCsvData() {
        try (FileInputStream fis = new FileInputStream(importDataProperties.getFilePath())) {
            List<Fair> fairs = CsvHelper.csvToFairs(fis);
            fairService.saveAll(fairs);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
