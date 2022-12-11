package com.bochi.fairapi.helper;

import com.bochi.fairapi.core.exception.FailedToLoadFileException;
import com.bochi.fairapi.domain.Fair;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Csv helper.
 */
@Slf4j
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CsvHelper {

    /**
     * Parses csv file to list of Fair object.
     *
     * @param is file input stream of the csv
     * @return the list of fairs
     */
    public static List<Fair> csvToFairs(InputStream is) {
        try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
             CSVParser csvParser = new CSVParser(fileReader,
                     CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim())) {

            List<Fair> fairs = new ArrayList<>();

            Iterable<CSVRecord> csvRecords = csvParser.getRecords();

            for (CSVRecord csvRecord : csvRecords) {
                if (csvRecord.isConsistent()) {
                    fairs.add(new Fair(
                            Long.parseLong(csvRecord.get("ID")),
                            csvRecord.get("LONG"),
                            csvRecord.get("LAT"),
                            csvRecord.get("SETCENS"),
                            csvRecord.get("AREAP"),
                            csvRecord.get("CODDIST"),
                            csvRecord.get("DISTRITO"),
                            csvRecord.get("CODSUBPREF"),
                            csvRecord.get("SUBPREFE"),
                            csvRecord.get("REGIAO5"),
                            csvRecord.get("REGIAO8"),
                            csvRecord.get("NOME_FEIRA"),
                            csvRecord.get("REGISTRO"),
                            csvRecord.get("LOGRADOURO"),
                            csvRecord.get("NUMERO"),
                            csvRecord.get("BAIRRO"),
                            csvRecord.get("REFERENCIA")
                    ));
                }
                else {
                    log.info("Skipping inconsistent.");
                }
            }

            return fairs;
        } catch (IOException e) {
            throw new FailedToLoadFileException("fail to parse CSV file: " + e.getMessage());
        }
    }

}
