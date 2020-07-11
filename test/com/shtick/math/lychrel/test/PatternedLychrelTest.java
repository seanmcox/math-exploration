package com.shtick.math.lychrel.test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.LinkedList;

import org.junit.jupiter.api.Test;

import com.shtick.math.lychrel.PatternedLychrel;
import com.shtick.math.lychrel.pattern.AbsoluteConstant;
import com.shtick.math.lychrel.pattern.AdditionGroup;
import com.shtick.math.lychrel.pattern.BaseConstant;
import com.shtick.math.lychrel.pattern.ConstantGroup;
import com.shtick.math.lychrel.pattern.ExpressionPattern;
import com.shtick.math.lychrel.pattern.MultiplicationGroup;
import com.shtick.math.lychrel.pattern.PatternSize;
import com.shtick.math.lychrel.pattern.RelativeConstant;


class PatternedLychrelTest {

	@Test
	void testContructor() {
		{
			LinkedList<ExpressionPattern> expressionPatterns = new LinkedList<>();
			{
				ExpressionPattern pattern = new ExpressionPattern(new PatternSize(null,1), 0, new AdditionGroup(new MultiplicationGroup[] {new MultiplicationGroup(5, null), new MultiplicationGroup(1, new BaseConstant())}));
				expressionPatterns.add(pattern);
			}
			PatternedLychrel lychrel = new PatternedLychrel(PatternedLychrel.BASE_TYPE_INFINITE,expressionPatterns);
			assertEquals("5+b", lychrel.toString());
			assertEquals(PatternedLychrel.BASE_TYPE_INFINITE,lychrel.getBaseType());
		}

		{
			LinkedList<ExpressionPattern> expressionPatterns = new LinkedList<>();
			{
				ExpressionPattern pattern = new ExpressionPattern(new PatternSize(null,1), 0, new AdditionGroup(new MultiplicationGroup[] {new MultiplicationGroup(5, null), new MultiplicationGroup(1, new BaseConstant())}));
				expressionPatterns.add(pattern);
			}
			{
				ConstantGroup constantGroupA = new ConstantGroup(-1,'a');
				ExpressionPattern pattern = new ExpressionPattern(new PatternSize(constantGroupA,0), 0, new AdditionGroup(new MultiplicationGroup[] {new MultiplicationGroup(5, new RelativeConstant(0)), new MultiplicationGroup(1, new AbsoluteConstant(constantGroupA, 2))}));
				expressionPatterns.add(pattern);
				pattern = new ExpressionPattern(new PatternSize(constantGroupA,0), -1, new AdditionGroup(new MultiplicationGroup[] {new MultiplicationGroup(5, new RelativeConstant(0)), new MultiplicationGroup(1, new AbsoluteConstant(constantGroupA, 2))}));
				expressionPatterns.add(pattern);
			}
			{
				ConstantGroup constantGroupC = new ConstantGroup(10,'c');
				ExpressionPattern pattern = new ExpressionPattern(new PatternSize(constantGroupC,-2), 1, new AdditionGroup(new MultiplicationGroup[] {new MultiplicationGroup(5, new RelativeConstant(0)), new MultiplicationGroup(1, new AbsoluteConstant(constantGroupC, -2))}));
				expressionPatterns.add(pattern);
			}
			PatternedLychrel lychrel = new PatternedLychrel(PatternedLychrel.BASE_TYPE_UNKNOWN,expressionPatterns);
			assertEquals("5+b, 5*a[0]+a[2], ..., a[2]+5*a[-1], a[2]+5*a[-1], ..., 5*a[0]+a[2], 5*c[1]+c[8], 5*c[2]+c[8], 5*c[3]+c[8], 5*c[4]+c[8], 5*c[5]+c[8], 5*c[6]+c[8], 5*c[7]+c[8], 5*c[8]+c[8]", lychrel.toString());
			assertEquals(PatternedLychrel.BASE_TYPE_UNKNOWN,lychrel.getBaseType());
		}
	}

	@Test
	void testNextLychrel() {
		{
			LinkedList<ExpressionPattern> expressionPatterns = new LinkedList<>();
			{
				ExpressionPattern pattern = new ExpressionPattern(new PatternSize(null,4), 0, new AdditionGroup(new MultiplicationGroup[] {new MultiplicationGroup(-1, null), new MultiplicationGroup(1, new BaseConstant())}));
				expressionPatterns.add(pattern);
			}
			PatternedLychrel lychrel = new PatternedLychrel(3,expressionPatterns);
			assertEquals("-1+b, -1+b, -1+b, -1+b", lychrel.toString());
			assertEquals(3,lychrel.getBaseType());
			lychrel = lychrel.nextLychrelNumber();
			assertEquals("1, -1+b, -1+b, -1+b, -2+b", lychrel.toString());
			assertEquals(3,lychrel.getBaseType());
			lychrel = lychrel.nextLychrelNumber();
			assertEquals("1, 0, -1+b, -1+b, -2+b, -1+b", lychrel.toString());
			assertEquals(3,lychrel.getBaseType());
			lychrel = lychrel.nextLychrelNumber();
			assertEquals("1, 0, -1+b, -1+b, -2+b, -1+b, 0", lychrel.toString());
			assertEquals(3,lychrel.getBaseType());
			lychrel = lychrel.nextLychrelNumber();
			assertEquals("2, 0, -2+b, -1+b, -3+b, -1+b, 1", lychrel.toString());
			assertEquals(3,lychrel.getBaseType());
			lychrel = lychrel.nextLychrelNumber();
			assertEquals("4, 0, -4+b, -1+b, -5+b, -1+b, 3", lychrel.toString());
			assertEquals(3,lychrel.getBaseType());
		}
	}

	@Test
	void testIsLychrelStop() {
		{
			LinkedList<ExpressionPattern> expressionPatterns = new LinkedList<>();
			{
				ExpressionPattern pattern = new ExpressionPattern(new PatternSize(null,4), 0, new AdditionGroup(new MultiplicationGroup[] {new MultiplicationGroup(-1, null), new MultiplicationGroup(1, new BaseConstant())}));
				expressionPatterns.add(pattern);
			}
			PatternedLychrel lychrel = new PatternedLychrel(PatternedLychrel.BASE_TYPE_UNKNOWN,expressionPatterns);
			assertTrue(lychrel.isLychrelStop());
			lychrel = lychrel.nextLychrelNumber();
			assertTrue(lychrel.isLychrelStop());
			lychrel = lychrel.nextLychrelNumber();
			assertTrue(lychrel.isLychrelStop());
			lychrel = lychrel.nextLychrelNumber();
			assertFalse(lychrel.isLychrelStop());
			lychrel = lychrel.nextLychrelNumber();
			assertFalse(lychrel.isLychrelStop());
			lychrel = lychrel.nextLychrelNumber();
			assertFalse(lychrel.isLychrelStop());
		}

		{
			LinkedList<ExpressionPattern> expressionPatterns = new LinkedList<>();
			{
				ExpressionPattern pattern = new ExpressionPattern(new PatternSize(null,4), 0, new AdditionGroup(new MultiplicationGroup[] {new MultiplicationGroup(-1, null), new MultiplicationGroup(1, new BaseConstant())}));
				expressionPatterns.add(pattern);
			}
			PatternedLychrel lychrel = new PatternedLychrel(PatternedLychrel.BASE_TYPE_INFINITE,expressionPatterns);
			assertTrue(lychrel.isLychrelStop());
			lychrel = lychrel.nextLychrelNumber();
			assertFalse(lychrel.isLychrelStop());
			lychrel = lychrel.nextLychrelNumber();
			assertFalse(lychrel.isLychrelStop());
			lychrel = lychrel.nextLychrelNumber();
			assertFalse(lychrel.isLychrelStop());
			lychrel = lychrel.nextLychrelNumber();
			assertFalse(lychrel.isLychrelStop());
			lychrel = lychrel.nextLychrelNumber();
			assertFalse(lychrel.isLychrelStop());
		}

		{
			LinkedList<ExpressionPattern> expressionPatterns = new LinkedList<>();
			{
				ExpressionPattern pattern = new ExpressionPattern(new PatternSize(null,4), 0, new AdditionGroup(new MultiplicationGroup[] {new MultiplicationGroup(-1, null), new MultiplicationGroup(1, new BaseConstant())}));
				expressionPatterns.add(pattern);
			}
			PatternedLychrel lychrel = new PatternedLychrel(2,expressionPatterns);
			assertTrue(lychrel.isLychrelStop());
			lychrel = lychrel.nextLychrelNumber();
			assertFalse(lychrel.isLychrelStop());
			lychrel = lychrel.nextLychrelNumber();
			assertTrue(lychrel.isLychrelStop());
			lychrel = lychrel.nextLychrelNumber();
			assertFalse(lychrel.isLychrelStop());
			lychrel = lychrel.nextLychrelNumber();
			assertFalse(lychrel.isLychrelStop());
			lychrel = lychrel.nextLychrelNumber();
			assertFalse(lychrel.isLychrelStop());
		}

		{
			LinkedList<ExpressionPattern> expressionPatterns = new LinkedList<>();
			{
				ExpressionPattern pattern = new ExpressionPattern(new PatternSize(null,4), 0, new AdditionGroup(new MultiplicationGroup[] {new MultiplicationGroup(-1, null), new MultiplicationGroup(1, new BaseConstant())}));
				expressionPatterns.add(pattern);
			}
			PatternedLychrel lychrel = new PatternedLychrel(3,expressionPatterns);
			assertTrue(lychrel.isLychrelStop());
			lychrel = lychrel.nextLychrelNumber();
			assertTrue(lychrel.isLychrelStop());
			lychrel = lychrel.nextLychrelNumber();
			assertFalse(lychrel.isLychrelStop());
			lychrel = lychrel.nextLychrelNumber();
			assertFalse(lychrel.isLychrelStop());
			lychrel = lychrel.nextLychrelNumber();
			assertFalse(lychrel.isLychrelStop());
			lychrel = lychrel.nextLychrelNumber();
			assertFalse(lychrel.isLychrelStop());
		}
	}
	
}
