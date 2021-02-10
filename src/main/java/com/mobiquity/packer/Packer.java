package com.mobiquity.packer;

import com.mobiquity.exception.APIException;
import com.mobiquity.model.PackageMetaData;
import com.mobiquity.model.Result;
import com.mobiquity.service.PackageKnapsackSolver;
import com.mobiquity.utils.Parser;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.apache.log4j.Logger;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.nio.charset.StandardCharsets.UTF_8;

public class Packer {

    private final static Logger LOG = Logger.getLogger(Packer.class.getName());
    private final static PackageKnapsackSolver solver;

    static {
        solver = new PackageKnapsackSolver();
    }

    /**
     * A method which reads the CSV file and returns
     * the string result after solving for each CSV line record
     *
     * @param filePath
     * @return
     * @throws APIException
     */
    public static String pack(String filePath) throws APIException {
        LOG.info(String.format("FileName %s. Reading records from CSV file", filePath));
        List<PackageMetaData> packageMetaData = readRecords(filePath);
        LOG.info("Solving for maximum value for each record in CSV file");
        List<Result> result = solvePackageKnapsack(solver, packageMetaData);
        LOG.info("Formatting result to String");
        return formatResult(result);
    }

    /**
     * Parse the CSV file and converts each record to a PackageMetaData
     * where each record is treated as a fresh set for Knapsack solution
     *
     * @param filePath
     * @return List<PackageMetaData>
     * @throws APIException
     */
    private static List<PackageMetaData> readRecords(String filePath) throws APIException {
        LOG.debug("Reading records from the CSV file");
        List<PackageMetaData> packageMetaData = new ArrayList<>();
        try (Reader reader = new FileReader(filePath, UTF_8);
             CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withIgnoreHeaderCase().withDelimiter(':').withTrim())) {
            for (CSVRecord record : csvParser) {
                PackageMetaData packageRecordData = Parser.parse(record);
                packageMetaData.add(packageRecordData);
            }
        } catch (IOException e) {
            throw new APIException(String.format("Cannot parse CSV File %s, check the file !!!", filePath));
        }
        return packageMetaData;
    }

    /**
     * Solves the knapsack puzzle for each new record in the CSV,
     * translated to PackageMetaData in the earlier step
     *
     * @param solver
     * @param packageMetaData
     * @return List<Result>
     */
    private static List<Result> solvePackageKnapsack(PackageKnapsackSolver solver, List<PackageMetaData> packageMetaData) {
        LOG.debug("Solving maximum value for each CSV record");
        return packageMetaData.stream()
                .map(metaData -> solver.solve(metaData.getPackageCapacity(), metaData.getItems()))
                .collect(Collectors.toList());
    }

    /**
     * Returns a string with the results generated from solving
     * each record line from the CSV
     *
     * @param results
     * @return String
     */
    private static String formatResult(List<Result> results) {
        LOG.debug("Format result to String");
        StringBuffer stringBuffer = new StringBuffer();
        results.forEach(result -> {
            if (result.getItems() != null && !result.getItems().isEmpty()) {
                stringBuffer.append(
                        result.getItems().stream()
                                .map(item -> item.getIndex().toString())
                                .collect(Collectors.joining(",")));
            } else {
                stringBuffer.append("-");
            }
            stringBuffer.append("\n");
        });
        return stringBuffer.toString();
    }
}
