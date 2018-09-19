package com.mark.taxCalculator;

public class TaxCalculator {
	public static void main(String[] args) {

	}

	public int taxPercent(int salary) {
		int tax = 0;
		if (salary >= 0 && salary <= 14999) {
			tax = 0;
		} else if (salary >= 15000 && salary <= 19999) {
			tax = 10;
		} else if (salary > 19999 && salary <= 29999) {
			tax = 15;
		} else if (salary > 29999 && salary <= 44999) {
			tax = 20;
		} else if (salary > 44999) {
			tax = 25;
		}
		return tax;
	}
	
	public int salaryDeduction(int salary) {
		return salary - salary*taxPercent(salary)/100;
	}
}
