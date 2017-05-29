package world.jumo.loanaggregatorv2.domain;

public class AggregatedLoan {
	private int count;
	private String tuple;
	private double amount;
	public AggregatedLoan(){
		this.setCount(0);
	}
	public AggregatedLoan(int count, String tuple, double amount){
		this.setCount(count);
		this.setTuple(tuple);
		this.setAmount(amount);
	}
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}

	public String getTuple() {
		return tuple;
	}

	public void setTuple(String tuple) {
		this.tuple = tuple;
	}
}
