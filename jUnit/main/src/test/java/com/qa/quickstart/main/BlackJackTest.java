package com.qa.quickstart.main;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

@RunWith(Parameterized.class)
public class BlackJackTest {

	@Parameters
	public static List<Integer[]> data() {
		return Arrays.asList(new Integer[][]{{10, 10, 22}, {9, 22, 9}, {0, 22, 22}, {10, 10, 7}, {19, 1, 19}});
	}
	
	private int Input1;
	private int Input2;
	private int Expected;
	
	public BlackJackTest(int expected, int input1, int input2) {
		Input1 = input1;
		Input2 = input2;
		Expected = expected;
	}
	
	@Test
	public void test() {
		Blackjack blackjack = new Blackjack();
		
		assertEquals(Expected, blackjack.shuffle(Input1,Input2));

	}

}
