package com.shtick.math.lychrel.pattern.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.shtick.math.lychrel.pattern.AbsoluteConstant;
import com.shtick.math.lychrel.pattern.BaseConstant;
import com.shtick.math.lychrel.pattern.Constant;
import com.shtick.math.lychrel.pattern.ConstantGroup;
import com.shtick.math.lychrel.pattern.MultiplicationGroup;
import com.shtick.math.lychrel.pattern.RelativeConstant;


class MultiplicationGroupTest {

	@Test
	void testContructor() {
		{
			MultiplicationGroup multiplicationGroup = new MultiplicationGroup(0, null);
			assertEquals(0, multiplicationGroup.getFactor());
			assertNull(multiplicationGroup.getConstant());
			assertTrue(multiplicationGroup.isSimpleNumber());
		}

		{
			MultiplicationGroup multiplicationGroup = new MultiplicationGroup(5, null);
			assertEquals(5, multiplicationGroup.getFactor());
			assertNull(multiplicationGroup.getConstant());
			assertTrue(multiplicationGroup.isSimpleNumber());
		}

		{
			MultiplicationGroup multiplicationGroup = new MultiplicationGroup(5, new BaseConstant());
			assertEquals(5, multiplicationGroup.getFactor());
			assertEquals(new BaseConstant(), multiplicationGroup.getConstant());
			assertFalse(multiplicationGroup.isSimpleNumber());
		}

		{
			MultiplicationGroup multiplicationGroup = new MultiplicationGroup(0, new BaseConstant());
			assertEquals(0, multiplicationGroup.getFactor());
			assertNull(multiplicationGroup.getConstant());
			assertTrue(multiplicationGroup.isSimpleNumber());
		}
	}

	@Test
	void testCanCombine() {
		ConstantGroup constantGroup1 = new ConstantGroup(-1);
		ConstantGroup constantGroup2 = new ConstantGroup(-1);
		Constant[] constants = new Constant[] {null, new BaseConstant(), new AbsoluteConstant(constantGroup1, 1), new AbsoluteConstant(constantGroup1, 2), new AbsoluteConstant(constantGroup2, 1), new RelativeConstant(3)};
		MultiplicationGroup[] multiplicationGroups1 = new MultiplicationGroup[constants.length];
		MultiplicationGroup[] multiplicationGroups2 = new MultiplicationGroup[constants.length];
		for(int i=0;i<constants.length;i++)
			multiplicationGroups1[i] = new MultiplicationGroup(5, constants[i]) ;
		for(int i=0;i<constants.length;i++)
			multiplicationGroups2[i] = new MultiplicationGroup(1, constants[i]); 
		for(int i=0;i<multiplicationGroups1.length;i++) {
			for(int j=0;j<multiplicationGroups1.length;j++) {
				if(i==j)
					assertTrue(multiplicationGroups1[i].canCombine(multiplicationGroups1[j]));
				else
					assertFalse(multiplicationGroups1[i].canCombine(multiplicationGroups1[j]));
			}
		}
		for(int i=0;i<multiplicationGroups1.length;i++)
			assertTrue(multiplicationGroups1[i].canCombine(multiplicationGroups2[i]));
	}

	@Test
	void testAddSubtract() {
		ConstantGroup constantGroup1 = new ConstantGroup(-1);
		ConstantGroup constantGroup2 = new ConstantGroup(-1);
		Constant[] constants = new Constant[] {null, new BaseConstant(), new AbsoluteConstant(constantGroup1, 1), new AbsoluteConstant(constantGroup1, 2), new AbsoluteConstant(constantGroup2, 1), new RelativeConstant(3)};
		MultiplicationGroup[] multiplicationGroups1 = new MultiplicationGroup[constants.length];
		MultiplicationGroup[] multiplicationGroups2 = new MultiplicationGroup[constants.length];
		for(int i=0;i<constants.length;i++)
			multiplicationGroups1[i] = new MultiplicationGroup(5, constants[i]) ;
		for(int i=0;i<constants.length;i++)
			multiplicationGroups2[i] = new MultiplicationGroup(1, constants[i]); 
		for(int i=0;i<multiplicationGroups1.length;i++) {
			for(int j=0;j<multiplicationGroups1.length;j++) {
				if(i==j) {
					try {
						MultiplicationGroup add = multiplicationGroups1[i].add(multiplicationGroups2[j]);
						assertEquals(6,add.getFactor());
						assertEquals(constants[i],add.getConstant());
					}
					catch(Throwable t) {
						fail("Exception not expected: "+t.getMessage());
					}
					try {
						MultiplicationGroup sub = multiplicationGroups1[i].subtract(multiplicationGroups2[j]);
						assertEquals(4,sub.getFactor());
						assertEquals(constants[i],sub.getConstant());
					}
					catch(Throwable t) {
						fail("Exception not expected: "+t.getMessage());
					}
					try {
						MultiplicationGroup sub = multiplicationGroups1[i].subtract(multiplicationGroups1[j]);
						assertEquals(0,sub.getFactor());
						assertNull(sub.getConstant());
					}
					catch(Throwable t) {
						fail("Exception not expected: "+t.getMessage());
					}
				}
				else {
					try {
						multiplicationGroups1[i].add(multiplicationGroups2[j]);
						fail("Exception expected.");
					}
					catch(Throwable t) {
					}
					try {
						multiplicationGroups1[i].subtract(multiplicationGroups2[j]);
						fail("Exception expected.");
					}
					catch(Throwable t) {
					}
				}
			}
		}
	}

	@Test
	void testHasRelativeConstant() {
		ConstantGroup constantGroup1 = new ConstantGroup(-1);
		ConstantGroup constantGroup2 = new ConstantGroup(-1);
		Constant[] constants = new Constant[] {null, new BaseConstant(), new AbsoluteConstant(constantGroup1, 1), new AbsoluteConstant(constantGroup1, 2), new AbsoluteConstant(constantGroup2, 1), new RelativeConstant(3)};
		MultiplicationGroup[] multiplicationGroups = new MultiplicationGroup[constants.length];
		for(int i=0;i<constants.length;i++)
			multiplicationGroups[i] = new MultiplicationGroup(5, constants[i]) ;
		for(int i=0;i<multiplicationGroups.length;i++)
			assertEquals(constants[i] instanceof RelativeConstant,multiplicationGroups[i].hasRelativeConstant());
	}

	@Test
	void testGetTransposed() {
		ConstantGroup constantGroup1 = new ConstantGroup(-1);
		ConstantGroup constantGroup2 = new ConstantGroup(-1);
		Constant[] constants = new Constant[] {null, new BaseConstant(), new AbsoluteConstant(constantGroup1, 1), new AbsoluteConstant(constantGroup1, 2), new AbsoluteConstant(constantGroup2, 1), new RelativeConstant(3)};
		MultiplicationGroup[] multiplicationGroups = new MultiplicationGroup[constants.length];
		for(int i=0;i<constants.length;i++)
			multiplicationGroups[i] = new MultiplicationGroup(5, constants[i]) ;
		for(int i=0;i<multiplicationGroups.length;i++) {
			if(constants[i] == null)
				assertEquals(constants[i],multiplicationGroups[i].getTransposed(3).getConstant());
			else if(constants[i] instanceof RelativeConstant)
				assertEquals(new RelativeConstant(((RelativeConstant)constants[i]).getOffset()+3),multiplicationGroups[i].getTransposed(3).getConstant());
			else
				assertEquals(constants[i],multiplicationGroups[i].getTransposed(3).getConstant());
		}
	}
}
