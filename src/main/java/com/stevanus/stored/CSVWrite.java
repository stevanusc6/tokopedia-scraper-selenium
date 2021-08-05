package com.stevanus.stored;

import com.stevanus.model.Product;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import java.io.*;
import java.util.Arrays;
import java.util.List;

public class CSVWrite {

    private static final String[] CSV_HEADER = {"name","description","imagelink","price","rating","merchant"};
    private FileWriter fileWriter;
    private CSVPrinter csvPrinter;

    /**
     * function for generate csv file from
     * @param productList
     * @param fileName
     * @throws FileNotFoundException
     * @throws UnsupportedEncodingException
     */
    public void writeToCSV(List<Product> productList, String fileName) throws FileNotFoundException, UnsupportedEncodingException {

        try {
            fileWriter = new FileWriter(fileName);
            csvPrinter = new CSVPrinter(fileWriter, CSVFormat.DEFAULT.withHeader(CSV_HEADER).withQuote('"'));

            for(Product product : productList){
               List data = Arrays.asList(
                       product.getName(),
                       product.getDescription(),
                       product.getImageLink(),
                       product.getPrice(),
                       product.getRating(),
                       product.getMerchant()
               );
                csvPrinter.printRecord(data);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                fileWriter.flush();
                fileWriter.close();
                csvPrinter.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }



    }

}
