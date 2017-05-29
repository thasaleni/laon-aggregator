package world.jumo.loanaggregatorv2;

import java.io.File;
import java.util.List;
import java.util.Map;

import world.jumo.loanaggregatorv2.domain.AggregatedLoan;
import world.jumo.loanaggregatorv2.domain.Loan;
import world.jumo.loanaggregatorv2.service.CsvReaderService;
import world.jumo.loanaggregatorv2.service.CsvWriterService;
import world.jumo.loanaggregatorv2.service.LoanAggregatorService;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ){
    	String csvPath = args[0];
    	File csvInputFile = new File(csvPath);
    	if(!csvInputFile.exists()){
    		System.out.println("file "+csvInputFile+" doesn't exiast, please enter valid file.");
    	}
    	
    	List<Loan> loans = CsvReaderService.readCsvFile(csvPath);
    	Map<String, AggregatedLoan> aggreagatedLoans = LoanAggregatorService.aggregate(loans);
    	CsvWriterService.writeCsvFile(aggreagatedLoans);
    }
    
}
