package com.shtick.math.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.shtick.math.BigInt;

class BigIntTest {

	@Test
	void testConstructors() {
		{
			BigInt n = new BigInt(10, "31459");
			assertEquals(10, n.getBase());
			assertArrayEquals(new int[] {3,1,4,5,9},n.getDigits(),"String value, base 10.");
		}

		{
			BigInt n = new BigInt(16, "FA3C9");
			assertEquals(16, n.getBase());
			assertArrayEquals(new int[] {15,10,3,12,9},n.getDigits(),"String value, base 16.");
		}

		try{
			BigInt n = new BigInt(10, "FA3C9");
			fail("String value failure case succeeded.");
		}
		catch(Throwable t) {
			// Expected case.
		}
		
		{
			BigInt n = new BigInt(10, "0");
			assertEquals(10, n.getBase());
			assertArrayEquals(new int[] {},n.getDigits(),"String value, 0, base 10.");
		}
		
		{
			BigInt n = new BigInt(10, new int[] {3,1,4,5,9});
			assertEquals(10, n.getBase());
			assertArrayEquals(new int[] {3,1,4,5,9},n.getDigits(),"Array value, base 10.");
		}

		{
			BigInt n = new BigInt(16, new int[] {15,10,3,12,9});
			assertEquals(16, n.getBase());
			assertArrayEquals(new int[] {15,10,3,12,9},n.getDigits(),"Array value, base 16.");
		}

		try{
			BigInt n = new BigInt(10, new int[] {15,10,3,12,9});
			fail("Array value failure case succeeded.");
		}
		catch(Throwable t) {
			// Expected case.
		}
		
		{
			BigInt n = new BigInt(10, new int[]{});
			assertEquals(10, n.getBase());
			assertArrayEquals(new int[] {},n.getDigits(),"Array value, 0, base 10.");
		}
		
		{
			BigInt n = new BigInt(10, 31459);
			assertEquals(10, n.getBase());
			assertArrayEquals(new int[] {3,1,4,5,9},n.getDigits(),"Int value, base 10.");
		}

		{
			BigInt n = new BigInt(16, 0xFA3C9);
			assertEquals(16, n.getBase());
			assertArrayEquals(new int[] {15,10,3,12,9},n.getDigits(),"Int value, base 16.");
		}
		
		{
			BigInt n = new BigInt(10, 0);
			assertEquals(10, n.getBase());
			assertArrayEquals(new int[] {},n.getDigits(),"Int value, 0, base 10.");
		}
	}

	@Test
	void testMultiplication() {
		{
			BigInt n = new BigInt(10, 3);
			BigInt n2 = new BigInt(10, 2);
			BigInt result = n.multipliedBy(n2);
			assertEquals(10, result.getBase());
			assertArrayEquals(new int[] {6},result.getDigits(),"Expected multiplication result.");
		}
		
		{
			BigInt n = new BigInt(10, 40);
			BigInt n2 = new BigInt(10, 20);
			BigInt result = n.multipliedBy(n2);
			assertEquals(10, result.getBase());
			assertArrayEquals(new int[] {8,0,0},result.getDigits(),"Expected multiplication result.");
		}

		{
			BigInt n = new BigInt(10, 40);
			BigInt n2 = new BigInt(10, 0);
			BigInt result = n.multipliedBy(n2);
			assertEquals(10, result.getBase());
			assertArrayEquals(new int[] {},result.getDigits(),"Expected multiplication result.");
		}
	}
}
