package com.shtick.math.lychrel.pattern.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import com.shtick.math.lychrel.pattern.AbsoluteConstant;
import com.shtick.math.lychrel.pattern.AdditionGroup;
import com.shtick.math.lychrel.pattern.BaseConstant;
import com.shtick.math.lychrel.pattern.ConstantGroup;
import com.shtick.math.lychrel.pattern.MultiplicationGroup;
import com.shtick.math.lychrel.pattern.RelativeConstant;


class AdditionGroupTest {

	@Test
	void testContructor() {
		{
			MultiplicationGroup[] inputGroup = new MultiplicationGroup[] {new MultiplicationGroup(5, new BaseConstant())};
			AdditionGroup additionGroup = new AdditionGroup(inputGroup);
			MultiplicationGroup[] multiplicationGroups = additionGroup.getMultiplicationGroups();
			assertEquals(inputGroup.length,multiplicationGroups.length);
			for(int i=0;i<inputGroup.length;i++) {
				assertEquals(inputGroup[i].getFactor(),multiplicationGroups[i].getFactor());
				assertEquals(inputGroup[i].getConstant(),multiplicationGroups[i].getConstant());
			}
		}

		{
			MultiplicationGroup[] inputGroup = new MultiplicationGroup[] {new MultiplicationGroup(3, null), new MultiplicationGroup(5, new BaseConstant())};
			AdditionGroup additionGroup = new AdditionGroup(inputGroup);
			MultiplicationGroup[] multiplicationGroups = additionGroup.getMultiplicationGroups();
			assertEquals(inputGroup.length,multiplicationGroups.length);
			for(int i=0;i<inputGroup.length;i++) {
				assertEquals(inputGroup[i].getFactor(),multiplicationGroups[i].getFactor());
				assertEquals(inputGroup[i].getConstant(),multiplicationGroups[i].getConstant());
			}
		}

		{
			MultiplicationGroup[] inputGroup = new MultiplicationGroup[] {new MultiplicationGroup(5, new BaseConstant()), new MultiplicationGroup(3, null)};
			MultiplicationGroup[] inputGroupSorted = new MultiplicationGroup[] {new MultiplicationGroup(3, null), new MultiplicationGroup(5, new BaseConstant())};
			AdditionGroup additionGroup = new AdditionGroup(inputGroup);
			MultiplicationGroup[] multiplicationGroups = additionGroup.getMultiplicationGroups();
			assertEquals(inputGroup.length,multiplicationGroups.length);
			for(int i=0;i<inputGroup.length;i++) {
				assertEquals(inputGroupSorted[i].getFactor(),multiplicationGroups[i].getFactor());
				assertEquals(inputGroupSorted[i].getConstant(),multiplicationGroups[i].getConstant());
			}
		}

		{
			MultiplicationGroup[] inputGroup = new MultiplicationGroup[] {new MultiplicationGroup(1, new BaseConstant()), new MultiplicationGroup(-5, null), new MultiplicationGroup(1, new RelativeConstant(0))};
			MultiplicationGroup[] inputGroupSorted = new MultiplicationGroup[] {new MultiplicationGroup(-5, null), new MultiplicationGroup(1, new BaseConstant()), new MultiplicationGroup(1, new RelativeConstant(0))};
			AdditionGroup additionGroup = new AdditionGroup(inputGroup);
			MultiplicationGroup[] multiplicationGroups = additionGroup.getMultiplicationGroups();
			assertEquals(inputGroup.length,multiplicationGroups.length);
			for(int i=0;i<inputGroup.length;i++) {
				assertEquals(inputGroupSorted[i].getFactor(),multiplicationGroups[i].getFactor());
				assertEquals(inputGroupSorted[i].getConstant(),multiplicationGroups[i].getConstant());
			}
		}
	}

	@Test
	void testGetCarryStatus() {
		ConstantGroup constantGroup1 = new ConstantGroup(-1);

		{
			AdditionGroup additionGroup = new AdditionGroup(new MultiplicationGroup[] {new MultiplicationGroup(1, new BaseConstant())});
			assertEquals(1, additionGroup.getCarryStatus());
		}

		{
			AdditionGroup additionGroup = new AdditionGroup(new MultiplicationGroup[] {new MultiplicationGroup(5, null)});
			assertEquals(-1, additionGroup.getCarryStatus());
		}

		{
			AdditionGroup additionGroup = new AdditionGroup(new MultiplicationGroup[] {new MultiplicationGroup(1, new BaseConstant()), new MultiplicationGroup(-5, null), new MultiplicationGroup(1, new AbsoluteConstant(constantGroup1, 0))});
			assertEquals(0, additionGroup.getCarryStatus());
		}
	}

	@Test
	void testHasRelativeConstants() {
		ConstantGroup constantGroup1 = new ConstantGroup(-1);

		{
			AdditionGroup additionGroup = new AdditionGroup(new MultiplicationGroup[] {new MultiplicationGroup(1, new BaseConstant())});
			assertFalse(additionGroup.hasRelativeConstants());
		}

		{
			AdditionGroup additionGroup = new AdditionGroup(new MultiplicationGroup[] {new MultiplicationGroup(5, null)});
			assertFalse(additionGroup.hasRelativeConstants());
		}

		{
			AdditionGroup additionGroup = new AdditionGroup(new MultiplicationGroup[] {new MultiplicationGroup(1, new BaseConstant()), new MultiplicationGroup(-5, null), new MultiplicationGroup(1, new AbsoluteConstant(constantGroup1, 0))});
			assertFalse(additionGroup.hasRelativeConstants());
		}

		{
			AdditionGroup additionGroup = new AdditionGroup(new MultiplicationGroup[] {new MultiplicationGroup(1, new BaseConstant()), new MultiplicationGroup(-5, null), new MultiplicationGroup(1, new RelativeConstant(0))});
			assertTrue(additionGroup.hasRelativeConstants());
		}
	}

	@Test
	void testGetTransposed() {
		{
			MultiplicationGroup[] inputGroup = new MultiplicationGroup[] {new MultiplicationGroup(5, new BaseConstant()), new MultiplicationGroup(3, null)};
			MultiplicationGroup[] inputGroupSorted = new MultiplicationGroup[] {new MultiplicationGroup(3, null), new MultiplicationGroup(5, new BaseConstant())};
			AdditionGroup additionGroup = new AdditionGroup(inputGroup);
			MultiplicationGroup[] multiplicationGroups = additionGroup.getTransposed(5).getMultiplicationGroups();
			assertEquals(inputGroup.length,multiplicationGroups.length);
			for(int i=0;i<inputGroup.length;i++) {
				assertEquals(inputGroupSorted[i].getFactor(),multiplicationGroups[i].getFactor());
				assertEquals(inputGroupSorted[i].getConstant(),multiplicationGroups[i].getConstant());
			}
		}

		{
			MultiplicationGroup[] inputGroup = new MultiplicationGroup[] {new MultiplicationGroup(1, new BaseConstant()), new MultiplicationGroup(-5, null), new MultiplicationGroup(1, new RelativeConstant(0))};
			MultiplicationGroup[] expectedGroup = new MultiplicationGroup[] {new MultiplicationGroup(-5, null), new MultiplicationGroup(1, new BaseConstant()), new MultiplicationGroup(1, new RelativeConstant(5))};
			AdditionGroup additionGroup = new AdditionGroup(inputGroup);
			MultiplicationGroup[] multiplicationGroups = additionGroup.getTransposed(5).getMultiplicationGroups();
			assertEquals(inputGroup.length,multiplicationGroups.length);
			for(int i=0;i<inputGroup.length;i++) {
				assertEquals(expectedGroup[i].getFactor(),multiplicationGroups[i].getFactor());
				assertEquals(expectedGroup[i].getConstant(),multiplicationGroups[i].getConstant());
			}
		}
	}
}
