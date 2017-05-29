
package world.jumo.loanaggregatorv2.service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVPrinter;

import world.jumo.loanaggregatorv2.domain.AggregatedLoan;

public class CsvWriterService {
	
	private static final String NEW_LINE_SEPARATOR = "\n";
	
	private static final Object [] FILE_HEADER = {"Count","Tuple","Amount"};

	private static final String FILE_NAME = "output.csv";
	
	public static void writeCsvFile(Map<String, AggregatedLoan> loans) {
		FileWriter fileWriter = null;
		CSVPrinter csvFilePrinter = null;
        CSVFormat csvFileFormat = CSVFormat.DEFAULT.withRecordSeparator(NEW_LINE_SEPARATOR);
		try {
			fileWriter = new FileWriter(FILE_NAME);
	        csvFilePrinter = new CSVPrinter(fileWriter, csvFileFormat);
	        csvFilePrinter.printRecord(FILE_HEADER);
			for (Entry<String, AggregatedLoan> loan : loans.entrySet()) {
				List<String> aggregatedLoanDataRecord = new ArrayList<String>();
				aggregatedLoanDataRecord.add(String.valueOf(loan.getValue().getCount()));
				aggregatedLoanDataRecord.add(loan.getValue().getTuple());
				aggregatedLoanDataRecord.add(String.valueOf(loan.getValue().getAmount()));
	            csvFilePrinter.printRecord(aggregatedLoanDataRecord);
			}
			System.out.println("CSV file " +FILE_NAME+" was created successfully !!!");
		} catch (Exception e) {
			System.out.println("Error in CsvFileWriter !!!");
			e.printStackTrace();
		} finally {
			try {
				fileWriter.flush();
				fileWriter.close();
				csvFilePrinter.close();
			} catch (IOException e) {
				System.out.println("Error while flushing/closing fileWriter/csvPrinter !!!");
                e.printStackTrace();
			}
		}
	}
}