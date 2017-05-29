package world.jumo.loanaggregatorv2.service;

import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;

import world.jumo.loanaggregatorv2.domain.Loan;

public class CsvReaderService {

	private static final String[] FILE_HEADER_MAPPING = { "MSISDN", "Network", "Date", "Product", "Amount" };

	private static final String MSISDN = "MSISDN";
	private static final String NETWORK = "Network";
	private static final String DATE = "Date";
	private static final String PRODUCT = "Product";
	private static final String AMOUNT = "Amount";

	public static List<Loan> readCsvFile(String fileName) {
		List<Loan> result = new ArrayList<Loan>();
		FileReader fileReader = null;

		CSVParser csvFileParser = null;

		CSVFormat csvFileFormat = CSVFormat.RFC4180.withHeader(FILE_HEADER_MAPPING).withTrim().withQuote('\'');

		try {

			List<Loan> loans = new ArrayList<Loan>();
			fileReader = new FileReader(fileName);
			csvFileParser = new CSVParser(fileReader, csvFileFormat);
			List<CSVRecord> csvRecords = csvFileParser.getRecords();

			for (int i = 1; i < csvRecords.size(); i++) {
				CSVRecord record = csvRecords.get(i);
				DateFormat df = new SimpleDateFormat("dd-MMM-yyyy"); 
				Loan loan = new Loan(record.get(MSISDN), record.get(NETWORK), (df.parse(String.valueOf(record.get(DATE)))),
						record.get(PRODUCT), Double.parseDouble(record.get(AMOUNT)));
				loans.add(loan);
			}
			result.addAll(loans);
		} catch (Exception e) {
			System.out.println("Error in CsvFileReader !!!");
			e.printStackTrace();
		} finally {
			try {
				fileReader.close();
				csvFileParser.close();
			} catch (IOException e) {
				System.out.println("Error while closing fileReader/csvFileParser !!!");
				e.printStackTrace();
			}
		}
		return result;

	}
}
