package com.stevanus;

import com.stevanus.scraping.Scraping;
import com.stevanus.stored.CSVWrite;

import java.io.IOException;

public class MainProgram {
    public static void main(String[] args) throws IOException {
        Scraping scraping = new Scraping();
        scraping.getLinkProductList();

        CSVWrite csvWrite = new CSVWrite();
        csvWrite.writeToCSV(scraping.getProductList(), "result.csv");
        System.out.println("Process Finish");
    }
}
