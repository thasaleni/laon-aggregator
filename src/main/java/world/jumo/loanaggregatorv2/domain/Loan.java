package world.jumo.loanaggregatorv2.domain;

import java.util.Date;

public class Loan {
	
	private String msisdn;
	private String network;
	private Date date;
	private String product;
	private double amount;
	
	
	public Loan(String msisdn, String network, Date date, String product, double amount){
		this.msisdn = msisdn;
		this.network = network;
		this.date = date;
		this.product = product;
		this.amount = amount;
	}
	
	public String getMsisdn() {
		return msisdn;
	}
	public void setMsisdn(String msisdn) {
		this.msisdn = msisdn;
	}
	public String getNetwork() {
		return network;
	}
	public void setNetwork(String network) {
		this.network = network;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}

}
