package com.mark.taxCalculator;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;


@RunWith(Parameterized.class)
public class TaxCalculatorTest {

	@Parameters
	public static List<Integer[]> data() {
		return Arrays.asList(new Integer[][]{{0, 10000, 13000, 14999, 13000}, {10, 15000, 17000, 19999, 15300}, {15, 20000, 23000, 29999, 19550}, {20, 30000, 40000, 44999, 32000}, {25, 45000, 70000, 100000, 52500}});
	}
	
	TaxCalculator taxCalculator = new TaxCalculator();
	
	private int taxBracket;
	private int lowerBoundry;
	private int valueWithin;
	private int upperBoundry;
	private int salaryAfterDeduction;
	
	public TaxCalculatorTest(int bracket, int lBoundry, int vWithin, int uBoundry, int sDeduction) {
		taxBracket = bracket;
		lowerBoundry = lBoundry;
		valueWithin = vWithin;
		upperBoundry = uBoundry;
		salaryAfterDeduction = sDeduction;
	}
	
	@Test
	public void lowerBoundryTest() {
		assertEquals("Lower boundry hasn't passed", taxBracket, taxCalculator.taxPercent(lowerBoundry));
	}
	
	@Test
	public void valueWithinTest() {
		assertEquals("Value within boundries hasn't passed", taxBracket, taxCalculator.taxPercent(valueWithin));
	}
	
	@Test
	public void upperBoundryTest() {
		assertEquals("Upper boundy hasn't passed", taxBracket, taxCalculator.taxPercent(upperBoundry));
	}
	
	@Test
	public void salaryDeductionTest() {
		assertEquals("Salary deduction didn't work", salaryAfterDeduction, taxCalculator.salaryDeduction(valueWithin));
	}

}
