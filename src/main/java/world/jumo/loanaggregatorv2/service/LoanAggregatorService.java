package world.jumo.loanaggregatorv2.service;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Set;

import world.jumo.loanaggregatorv2.domain.AggregatedLoan;
import world.jumo.loanaggregatorv2.domain.Loan;

public class LoanAggregatorService {
	
	public static Map<String, AggregatedLoan> aggregate(List<Loan> loans) {
		List<AggregatedLoan> result = new ArrayList<AggregatedLoan>();
		Map<String, AggregatedLoan> totals = new HashMap<String, AggregatedLoan>();
		for (Loan loan : loans) {
			
			if(totals.get(getMonth(loan.getDate())) != null){
				AggregatedLoan aggLoan = totals.get(getMonth(loan.getDate()));
				aggLoan.setCount(aggLoan.getCount()+1);
				aggLoan.setTuple(getMonth(loan.getDate()));
				aggLoan.setAmount(aggLoan.getAmount() + loan.getAmount());
				totals.put(getMonth(loan.getDate()), aggLoan);
			}else{
				AggregatedLoan aggLoan = new AggregatedLoan();
				aggLoan.setCount(aggLoan.getCount()+1);
				aggLoan.setTuple(getMonth(loan.getDate()));
				aggLoan.setAmount(loan.getAmount());
				totals.put(getMonth(loan.getDate()), aggLoan);
			}
			if(totals.get(loan.getNetwork()) != null){
				AggregatedLoan aggLoan = totals.get(loan.getNetwork());
				aggLoan.setCount(aggLoan.getCount()+1);
				aggLoan.setTuple(loan.getNetwork());
				aggLoan.setAmount(aggLoan.getAmount() + loan.getAmount());
				totals.put(loan.getNetwork(), aggLoan);
			}else{
				AggregatedLoan aggLoan = new AggregatedLoan();;
				aggLoan.setCount(aggLoan.getCount()+1);
				aggLoan.setTuple(loan.getNetwork());
				aggLoan.setAmount(loan.getAmount());
				totals.put(loan.getNetwork(), aggLoan);
			}
			if(totals.get(loan.getProduct()) != null){
				AggregatedLoan aggLoan = totals.get(loan.getProduct());
				aggLoan.setCount(aggLoan.getCount()+1);
				aggLoan.setTuple(loan.getProduct());
				aggLoan.setAmount(aggLoan.getAmount() + loan.getAmount());
				totals.put(loan.getProduct(), aggLoan);
			}else{
				AggregatedLoan aggLoan = new AggregatedLoan();
				aggLoan.setCount(aggLoan.getCount()+1);
				aggLoan.setTuple(loan.getProduct());
				aggLoan.setAmount(loan.getAmount());
				totals.put(loan.getProduct(), aggLoan);
			}
		}
		return totals;
	}

	private static String getMonth(Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		String month = calendar.getDisplayName(Calendar.MONTH, Calendar.LONG, Locale.getDefault());
		return month;
	}
}
