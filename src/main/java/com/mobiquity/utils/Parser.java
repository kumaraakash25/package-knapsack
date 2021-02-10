package com.mobiquity.utils;

import com.mobiquity.exception.APIException;
import com.mobiquity.model.Item;
import com.mobiquity.model.PackageMetaData;
import org.apache.commons.csv.CSVRecord;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Parser {
    /**
     * Reads the records from the CSV File and converts each line to a PackageMetaData
     *
     * @param record
     * @return
     * @throws APIException
     */
    public static PackageMetaData parse(CSVRecord record) throws APIException {
        if (record.size() != 2) {
            throw new APIException(String.format("Incorrect record found at lime %d", record.getRecordNumber()));
        }
        Integer weight = Integer.parseInt(record.get(0));
        List<Item> items = new ArrayList<>();
        PackageMetaData packageMetaData = new PackageMetaData(weight, items);
        Matcher matcher = Pattern.compile("\\((.*?)\\)").matcher(record.get(1));
        while (matcher.find()) {
            String[] itemMetaData = matcher.group(1).split(",");
            Item item = new Item(
                    Integer.parseInt(itemMetaData[0]),
                    Double.parseDouble(itemMetaData[1]),
                    Integer.parseInt(itemMetaData[2].replace("€", "")));
            items.add(item);
        }
        return packageMetaData;
    }
}
