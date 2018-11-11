package fracCalc;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

public class FracCalcTest {

	public static class AdditionTests {
		@Test
		public void testAdditionSimple() {
			assertEquals("2/5", FracCalc.produceAnswer("1/5 + 1/5"));
			assertEquals("4/5", FracCalc.produceAnswer("3/5 + 1/5"));
			assertEquals("4_3/7", FracCalc.produceAnswer("1_1/7 + 3_2/7"));
		}
		
		@Test
		public void testAdditionReduce() {
			assertEquals("1_1/5", FracCalc.produceAnswer("3/5 + 3/5"));
			assertEquals("1_1/5", FracCalc.produceAnswer("4/5 + 2/5"));
			assertEquals("1/4", FracCalc.produceAnswer("1/8 + 1/8"));
		}
		
		@Test
		public void testAdditionWholeNumbers() {
			assertEquals("1", FracCalc.produceAnswer("2/5 + 3/5"));
			assertEquals("1", FracCalc.produceAnswer("2/3 + 1/3"));
			assertEquals("10", FracCalc.produceAnswer("3 + 7"));
			assertEquals("2", FracCalc.produceAnswer("1 + 1"));
			assertEquals("4", FracCalc.produceAnswer("1 + 3"));
			assertEquals("452", FracCalc.produceAnswer("452 + 0"));
			assertEquals("254", FracCalc.produceAnswer("0 + 254"));
			assertEquals("1021778", FracCalc.produceAnswer("124543 + 897235"));
		}
		
		@Test
		public void testAdditionWithNegatives() {
			assertEquals("2/5", FracCalc.produceAnswer("3/5 + -1/5"));
			assertEquals("900", FracCalc.produceAnswer("978 + -78"));
			assertEquals("900", FracCalc.produceAnswer("-78 + 978"));
			assertEquals("-1_1/4", FracCalc.produceAnswer("-3_3/4 + 2_2/4"));
			assertEquals("-1_1/4", FracCalc.produceAnswer("2_2/4 + -3_3/4"));
		}
		
		@Test
		public void testAdditionImproperFractionsAndReductions() {
			assertEquals("5_5/6", FracCalc.produceAnswer("20/8 + 3_1/3"));
			assertEquals("1_1/20", FracCalc.produceAnswer("4/5 + 2/8"));
		}
		
		@Test
		public void testAdditionCombined() {
			// This unit test tries a number of combined concepts
			assertEquals("-9035", FracCalc.produceAnswer("-9035 + 0"));
			assertEquals("-64", FracCalc.produceAnswer("64 + -128"));
			assertEquals("-133", FracCalc.produceAnswer("-98 + -35"));
			assertEquals("62_11/19", FracCalc.produceAnswer("0 + 34_543/19"));
			assertEquals("-44_229/888", FracCalc.produceAnswer("-38_3/72 + -4_82/37"));
		}
	}

	public static class SubtractionTests {
		@Test
		public void testSubtractionSimple() {
			assertEquals("1/5", FracCalc.produceAnswer("3/5 - 2/5"));
			assertEquals("0", FracCalc.produceAnswer("1/5 - 1/5"));
			assertEquals("0", FracCalc.produceAnswer("4_1/2 - 4_1/2"));
			
		}
		
		@Test
		public void testSubtractionReduce() {
			assertEquals("4/5", FracCalc.produceAnswer("9/10 - 1/10"));
			assertEquals("1/5", FracCalc.produceAnswer("5/10 - 3/10"));
		}
		
		@Test
		public void testSubtractionWholeNumbers() {
			assertEquals("0", FracCalc.produceAnswer("68591 - 68591"));
			assertEquals("7", FracCalc.produceAnswer("42 - 35"));
		}
		
		@Test
		public void testSubtractionWithNegatives() {
			assertEquals("-2/5", FracCalc.produceAnswer("2/5 - 4/5"));
			assertEquals("-7/8", FracCalc.produceAnswer("5_3/4 - 6_5/8"));
			assertEquals("-1_1/4", FracCalc.produceAnswer("-3_3/4 - -2_2/4"));
			assertEquals("-1_5/8", FracCalc.produceAnswer("4_1/2 - 5_9/8"));
			assertEquals("-1_1/8", FracCalc.produceAnswer("3_3/4 - 4_7/8"));
			assertEquals("6_1/4", FracCalc.produceAnswer("-3_3/4 - 2_2/4"));
			assertEquals("-36891", FracCalc.produceAnswer("48623 - 85514"));
			assertEquals("-9284", FracCalc.produceAnswer("0 - 9284"));
		}
		
		@Test
		public void testSubtractionImproperFractionsAndReductions() {
			assertEquals("53/96", FracCalc.produceAnswer("75/32 - 43/24"));
			assertEquals("16_23/24", FracCalc.produceAnswer("75/4 - 43/24"));
		}
		
		@Test
		public void testSubtractionCombined() {
			// This unit test tries a number of combined concepts
			assertEquals("12_3/8", FracCalc.produceAnswer("5_3/4 - -6_5/8"));
			assertEquals("8_5/21", FracCalc.produceAnswer("-12_3/7 - -20_2/3"));
			assertEquals("-65_247/336", FracCalc.produceAnswer("-32_75/16 - 27_43/21"));
		}

	}

	public static class MultiplicationTests {

		@Test
		public void testMultiplicationBasic() {
			assertEquals("3", FracCalc.produceAnswer("1_1/2 * 2"));
			assertEquals("6/25", FracCalc.produceAnswer("3/5 * 2/5"));
			assertEquals("164268", FracCalc.produceAnswer("234 * 702"));
			assertEquals("216", FracCalc.produceAnswer("12 * 18"));
			assertEquals("8", FracCalc.produceAnswer("12/3 * 2/1"));
			assertEquals("2", FracCalc.produceAnswer("16 * 1/8"));
			assertEquals("2", FracCalc.produceAnswer("3 * 2/3"));
			assertEquals("1_1/2", FracCalc.produceAnswer("6 * 1/4"));
			assertEquals("8994872", FracCalc.produceAnswer("1 * 8994872"));
			assertEquals("378/943", FracCalc.produceAnswer("27/41 * 14/23"));
			assertEquals("5_929/943", FracCalc.produceAnswer("1_27/41 * 3_14/23"));
		}
		
		@Test
		public void testMultiplicationWithNegatives() {
			assertEquals("-8", FracCalc.produceAnswer("12/3 * -2/1"));
			assertEquals("-8", FracCalc.produceAnswer("-12/3 * 2/1"));
			assertEquals("8", FracCalc.produceAnswer("-12/3 * -2/1"));
			assertEquals("-15_5/7", FracCalc.produceAnswer("-3_2/3 * 4_2/7"));
			assertEquals("-15_5/7", FracCalc.produceAnswer("3_2/3 * -4_2/7"));
			assertEquals("15_5/7", FracCalc.produceAnswer("-3_2/3 * -4_2/7"));
			assertEquals("-842346", FracCalc.produceAnswer("1 * -842346"));
			assertEquals("-75421", FracCalc.produceAnswer("-1 * 75421"));
			assertEquals("37953", FracCalc.produceAnswer("-1 * -37953"));
		}
		
		@Test
		public void testMultiplicationByZero() {
			assertEquals("0", FracCalc.produceAnswer("0 * 4/5"));
			assertEquals("0", FracCalc.produceAnswer("0 * 0"));
			assertEquals("0", FracCalc.produceAnswer("0 * 9321"));
			assertEquals("0", FracCalc.produceAnswer("0 * -5902"));
			assertEquals("0", FracCalc.produceAnswer("146 * 0"));
			assertEquals("0", FracCalc.produceAnswer("3_25/26 * 0"));
			assertEquals("0", FracCalc.produceAnswer("-24_1/3 * 0"));
		}
		
		@Test
		public void testMultiplicationCombined() {
			assertEquals("1065_115/168", FracCalc.produceAnswer("-32_75/16 * -27_43/21"));
			assertEquals("-15_67/943", FracCalc.produceAnswer("1_27/41 * -3_140/23"));
			assertEquals("4_2/3", FracCalc.produceAnswer("3_2/4 * 4/3"));
		}
	}

	public static class DivisionTests {

		@Test
		public void testDivisionBasic() {
			assertEquals("9/16", FracCalc.produceAnswer("3/4 / 4/3"));
			assertEquals("2_1/4", FracCalc.produceAnswer("3/2 / 2/3"));
			assertEquals("9457", FracCalc.produceAnswer("9457 / 1"));
			assertEquals("6/11", FracCalc.produceAnswer("6 / 11"));
			assertEquals("4/9", FracCalc.produceAnswer("4 / 9"));
			assertEquals("1", FracCalc.produceAnswer("23 / 23"));
			assertEquals("2_6/7", FracCalc.produceAnswer("20 / 7"));
			assertEquals("13/24", FracCalc.produceAnswer("1_1/12 / 2"));
			assertEquals("1", FracCalc.produceAnswer("3/4 / 3/4"));
		}
		
		@Test
		public void testDivisionWithNegatives() {
			assertEquals("1_5/8", FracCalc.produceAnswer("-13 / -8"));
			assertEquals("-2803", FracCalc.produceAnswer("-2803 / 1"));
			assertEquals("-12457", FracCalc.produceAnswer("12457 / -1"));
			assertEquals("45236", FracCalc.produceAnswer("-45236 / -1"));
			assertEquals("-2_6/7", FracCalc.produceAnswer("-20 / 7"));
			assertEquals("1_13/32", FracCalc.produceAnswer("-3_3/4 / -2_2/3"));
			assertEquals("-1_13/32", FracCalc.produceAnswer("-3_3/4 / 2_2/3"));
			assertEquals("-1_13/32", FracCalc.produceAnswer("3_3/4 / -2_2/3"));
		}
		
		@Test
		public void testDivisionWithZero() {
			assertEquals("0", FracCalc.produceAnswer("-0 / 98701"));
			assertEquals("0", FracCalc.produceAnswer("-0 / -98701"));
			assertEquals("0", FracCalc.produceAnswer("0 / -98701"));
			assertEquals("0", FracCalc.produceAnswer("0 / 37569"));
			assertEquals("0", FracCalc.produceAnswer("0 / 46/27"));
			assertEquals("0", FracCalc.produceAnswer("0/24 / 1/46"));
			assertEquals("0", FracCalc.produceAnswer("0/11 / 6/7"));
		}
		
		@Test
		public void testDivisionCombined() {
			assertEquals("2_2/3", FracCalc.produceAnswer("16/4 / 3/2"));
			assertEquals("-2_2/3", FracCalc.produceAnswer("16/4 / -3/2"));
			assertEquals("6_661/5520", FracCalc.produceAnswer("-38_3/72 / -4_82/37"));
			assertEquals("-5/21", FracCalc.produceAnswer("1_2/3 / -5_6/3"));
		}

	}
}
