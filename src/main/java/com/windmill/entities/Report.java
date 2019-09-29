package com.windmill.entities;

public class Report {
	private double avg;
	private double min;
	private double max;
	private double sum;
	
	public Report(Double avg, Double min, Double max, Double sum) {
		super();
		if(avg != null) {
			this.avg = avg;
		}
		if(min != null) {
			this.min = min;
		}
		if(max != null) {
			this.max = max;
		}
		if(sum != null) {
			this.sum = sum;
		}	
	}
	public double getAvg() {
		return avg;
	}
	public void setAvg(double avg) {
		this.avg = avg;
	}
	public double getMin() {
		return min;
	}
	public void setMin(double min) {
		this.min = min;
	}
	public double getMax() {
		return max;
	}
	public void setMax(double max) {
		this.max = max;
	}
	public double getSum() {
		return sum;
	}
	public void setSum(double sum) {
		this.sum = sum;
	}
}
