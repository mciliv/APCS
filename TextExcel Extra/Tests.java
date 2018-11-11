package textExcel;
// Do not modify this file.  This is version 2.
// New in version 2:
//  Checkpoint3 fixed to pass on complete final project without requiring any extra credit features.
//  New classes Checkpoint1Ex, Checkpoint2Ex, Checkpoint3Ex, all of which must pass on final project.
//  New class ExtraCreditCircularReferenceErrors (comment it out if you think it's interfering with other tests).

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class Tests
{
	public static class TestLocation implements Location
	{
		// Simple implementation of Location interface for use only by tests.
		private int row;
		private int col;

		public TestLocation(int row, int col)
		{
			this.row = row;
			this.col = col;
		}

		@Override
		public int getRow() {
			return row;
		}

		@Override
		public int getCol() {
			return col;
		}
	}

	public static class Helper
	{
		// For use only by test code, which uses it carefully.
		private String[][] items;

		public Helper()
		{
			items = new String[20][12];
			for (int i = 0; i < 20; i++)
				for (int j = 0; j < 12; j++)
					items[i][j] = format("");
		}

		public static String format(String s)
		{
			return String.format(String.format("%%-%d.%ds", 10, 10),  s);
		}

		public void setItem(int row, int col, String text)
		{
			items[row][col] = format(text);
		}

		public String getText()
		{
			String ret = "   |";
			for (int j = 0; j < 12; j++)
				ret = ret + format(Character.toString((char)('A' + j))) + "|";
			ret = ret + "\n";
			for (int i = 0; i < 20; i++)
			{
				ret += String.format("%-3d|", i + 1);
				for (int j = 0; j < 12; j++)
				{
					ret += items[i][j] + "|";
				}
				ret += "\n";
			}
			return ret;
		}
	}
	
	public static class Checkpoint1
	{
		// Tests for checkpoint 1.
		// Pass them all, plus ensure main loop until quit works, for full credit on checkpoint 1.
		// Note these must also pass for all subsequent checkpoints including final project.
		Grid grid;
		
		@Before
		public void initializeGrid()
		{
			grid = new Spreadsheet();
		}
		
		@Test
		public void testGetRows()
		{
			assertEquals("getRows", 20, grid.getRows());
		}
		
		@Test
		public void testGetCols()
		{
			assertEquals("getCols", 12, grid.getCols());
		}
		
		@Test
		public void testProcessCommand()
		{
			String str = grid.processCommand("");
			assertEquals("output from empty command", "", str);
		}
	}

	public static class Checkpoint1Ex
	{
		// Extended tests for checkpoint 1, which must pass on final project.
		Grid grid;
		
		@Before
		public void initializeGrid()
		{
			grid = new Spreadsheet();
		}
		
		@Test
		public void testProcessCommandNonliteralEmpty()
		{
			String input = " ".trim();
			String output = grid.processCommand(input);
			assertEquals("output from empty command", "", output);
		}
	}

	public static class Checkpoint2
	{
		// Tests for checkpoint 2.
		// Note these must also pass for all subsequent checkpoints including final project.
		Grid grid;
		
		@Before
		public void initializeGrid()
		{
			grid = new Spreadsheet();
		}
		
		@Test
		public void testEmptyGridCells()
		{
			for (int i = 0; i < grid.getRows(); i++)
				for (int j = 0; j < grid.getCols(); j++)
				{
					Cell cell = grid.getCell(new TestLocation(i, j));
					assertEquals("empty cell text", Helper.format(""), cell.abbreviatedCellText());
					assertEquals("empty inspection text", "", cell.fullCellText());
				}
		}
		
		@Test
		public void testEmptyGridText()
		{
			Helper helper = new Helper();
			assertEquals("empty grid", helper.getText(), grid.getGridText());
		}
		
		@Test
		public void testShortStringCell()
		{
			String hello = "Hello";
			grid.processCommand("A1 = \"" + hello + "\"");
			Cell helloCell = grid.getCell(new TestLocation(0,0));
			assertEquals("hello cell text", Helper.format(hello), helloCell.abbreviatedCellText());
			assertEquals("hello inspection text", "\"" + hello + "\"", helloCell.fullCellText());
		}
		
		@Test
		public void testLongShortStringCell()
		{
			String greeting = "Hello, world!";
			grid.processCommand("L20 = \"" + greeting + "\"");
			Cell greetingCell = grid.getCell(new TestLocation(19,11));
			assertEquals("greeting cell text", Helper.format(greeting), greetingCell.abbreviatedCellText());
			assertEquals("greeting inspection text", "\"" + greeting + "\"", greetingCell.fullCellText());
		}
		
		@Test
		public void testEmptyStringCell()
		{
			grid.processCommand("B2 = \"\"");
			Cell emptyStringCell = grid.getCell(new TestLocation(1,1));
			assertEquals("empty string cell text", Helper.format(""), emptyStringCell.abbreviatedCellText());
			assertEquals("empty string inspection text", "\"\"", emptyStringCell.fullCellText());
		}
		
		@Test
		public void testDifferentCellTypes()
		{
			grid.processCommand("C11 = \"hi\"");
			Cell stringCell = grid.getCell(new TestLocation(10, 2));
			Cell emptyCell = grid.getCell(new TestLocation(0,0));
			assertTrue("string cell implementation class must be different from empty cell",
					!emptyCell.getClass().equals(stringCell.getClass()));
		}
		
		@Test
		public void testClear()
		{
			grid.processCommand("A1 = \"first\"");
			grid.processCommand("D8 = \"second\"");
			grid.processCommand("clear");
			Cell cellFirst = grid.getCell(new TestLocation(0,0));
			Cell cellSecond = grid.getCell(new TestLocation(7, 3));
			assertEquals("cellFirst inspection text after clear", "", cellFirst.fullCellText());
			assertEquals("cellSecond inspection text after clear", "", cellSecond.fullCellText());
		}
		
		@Test
		public void testClearLocation()
		{
			grid.processCommand("A1 = \"first\"");
			grid.processCommand("D8 = \"second\"");
			grid.processCommand("clear A1");
			Cell cellFirst = grid.getCell(new TestLocation(0,0));
			Cell cellSecond = grid.getCell(new TestLocation(7, 3));
			assertEquals("cellFirst inspection text after clear", "", cellFirst.fullCellText());
			assertEquals("cellSecond inspection text after clear", "\"second\"", cellSecond.fullCellText());
		}
		
		@Test
		public void testProcessCommandInspection()
		{
			String empty = grid.processCommand("A1");
			assertEquals("inspection of empty cell", "", empty);
			grid.processCommand("A1 = \"first\"");
			String first = grid.processCommand("A1");
			assertEquals("inspection of string cell", "\"first\"", first);
		}
		
		@Test
		public void testProcessCommand()
		{
			Helper helper = new Helper();
			String gridOne = grid.processCommand("A1 = \"oNe\"");
			helper.setItem(0, 0, "oNe");
			assertEquals("grid with one string cell", helper.getText(), gridOne);
			String accessorOne = grid.getGridText();
			assertEquals("grid from accessor with one string cell", helper.getText(), accessorOne);
			String gridTwo = grid.processCommand("L20 = \"TWo\"");
			helper.setItem(19, 11, "TWo");
			assertEquals("grid from accessor with two string cells", helper.getText(), gridTwo);
			String gridOnlyTwo = grid.processCommand("clear A1");
			helper.setItem(0, 0, "");
			assertEquals("grid with only the second string cell", helper.getText(), gridOnlyTwo);
			String gridEmpty = grid.processCommand("clear");
			helper.setItem(19, 11, "");
			assertEquals("empty grid", helper.getText(), gridEmpty);
		}
		
		@Test
		public void testProcessCommandSpecialStrings()
		{
			String stringSpecial1 = "A1 = ( avg A2-A3 )";
			String stringSpecial2 = "A1 = ( 1 * 2 / 1 + 3 - 5 )";
			Helper helper = new Helper();
			String grid1 = grid.processCommand("B7 = \"" + stringSpecial1 + "\"");
			helper.setItem(6, 1, stringSpecial1);
			assertEquals("grid with one special string", helper.getText(), grid1);
			String grid2 = grid.processCommand("F13 = \"" + stringSpecial2 + "\"");
			helper.setItem(12, 5, stringSpecial2);
			assertEquals("grid with two special strings", helper.getText(), grid2);
			String inspectedSpecial1 = grid.getCell(new TestLocation(6,1)).fullCellText();
			assertEquals("inspected first special string", "\"" + stringSpecial1 + "\"", inspectedSpecial1);
			String inspectedSpecial2 = grid.getCell(new TestLocation(12,5)).fullCellText();
			assertEquals("inspected second special string", "\"" + stringSpecial2 + "\"", inspectedSpecial2);
		}
	}
	
	public static class Checkpoint2Ex
	{
		// Extended tests for checkpoint 2, which must pass for final project.
		Grid grid;
		
		@Before
		public void initializeGrid()
		{
			grid = new Spreadsheet();
		}
		
		@Test
		public void testLongStringCellNoSpaces()
		{
			String greeting = "ThisIsALongString";
			grid.processCommand("L2 = \"" + greeting + "\"");
			Cell greetingCell = grid.getCell(new TestLocation(1,11));
			assertEquals("greeting cell text", Helper.format(greeting), greetingCell.abbreviatedCellText());
			assertEquals("greeting inspection text", "\"" + greeting + "\"", greetingCell.fullCellText());
		}

		@Test
		public void testLowerCaseCellAssignment()
		{
			String text = "Cell";
			grid.processCommand("b5 = \"" + text + "\"");
			Cell cell = grid.getCell(new TestLocation(4, 1));
			assertEquals("cell text", Helper.format(text), cell.abbreviatedCellText());
			assertEquals("inspection text", "\"" + text + "\"", cell.fullCellText());
			String processText = grid.processCommand("b5");
			assertEquals("processed inspection text", "\"" + text + "\"", processText);
			String processText2 = grid.processCommand("B5");
			assertEquals("processed inspection text 2", "\"" + text + "\"", processText2);
		}
		
		@Test
		public void testLowerCaseCellProcessInspection()
		{
			grid.processCommand("B2 = \"\"");
			String processText = grid.processCommand("b2");
			assertEquals("processed inspection text", "\"\"", processText);
			grid.processCommand("c18 = \"3.1410\"");
			String processText2 = grid.processCommand("c18");
			assertEquals("processed inspection text 2", "\"3.1410\"", processText2);
		}
		
		@Test
		public void testMixedCaseClear()
		{
			grid.processCommand("A1 = \"first\"");
			grid.processCommand("D8 = \"second\"");
			grid.processCommand("CleaR");
			Cell cellFirst = grid.getCell(new TestLocation(0,0));
			Cell cellSecond = grid.getCell(new TestLocation(7, 3));
			assertEquals("cellFirst inspection text after clear", "", cellFirst.fullCellText());
			assertEquals("cellSecond inspection text after clear", "", cellSecond.fullCellText());
		}
		
		@Test
		public void textNonliteralClear()
		{
			String clear = " clear ".trim();
			grid.processCommand("A1 = \"first\"");
			grid.processCommand("D8 = \"second\"");
			grid.processCommand(clear);
			Cell cellFirst = grid.getCell(new TestLocation(0,0));
			Cell cellSecond = grid.getCell(new TestLocation(7, 3));
			assertEquals("cellFirst inspection text after clear", "", cellFirst.fullCellText());
			assertEquals("cellSecond inspection text after clear", "", cellSecond.fullCellText());
			String finalGrid = grid.getGridText();
			Helper th = new Helper();
			String emptyGrid = th.getText();
			assertEquals("empty grid", emptyGrid, finalGrid);
		}
		
		@Test
		public void testMixedCaseClearLocation()
		{
			grid.processCommand("A18 = \"first\"");
			grid.processCommand("D8 = \"second\"");
			grid.processCommand("clEAr a18");
			Cell cellFirst = grid.getCell(new TestLocation(17,0));
			Cell cellSecond = grid.getCell(new TestLocation(7, 3));
			assertEquals("cellFirst inspection text after clear", "", cellFirst.fullCellText());
			assertEquals("cellSecond inspection text after clear", "\"second\"", cellSecond.fullCellText());
			String processedCleared = grid.processCommand("A18");
			assertEquals("processed inspection after clear", "", processedCleared);
		}
		
		@Test
		public void testProcessCommandMoreSpecialStrings()
		{
			String[] specialStrings = new String[] { "clear", "(", " = ", "5", "4.3", "12/28/1998", "A1 = ( 1 / 1 )", "A20 = 1/1/2000", "A9 = 4.3", "abcdefgh", "abcdefghi", "abcdefghijk" };
			
			Helper helper = new Helper();
			for (int col = 0; col < specialStrings.length; col++)
			{
				for (int row = 5; row < 20; row += 10)
				{
					String cellName = Character.toString((char)('A' + col)) + (row + 1);
					helper.setItem(row,  col, specialStrings[col]);
					String sheet = grid.processCommand(cellName + " = \"" + specialStrings[col] + "\"");
					assertEquals("grid after setting cell " + cellName, helper.getText(), sheet);
					String inspected = grid.getCell(new TestLocation(row, col)).fullCellText();
					assertEquals("inspected cell " + cellName, "\"" + specialStrings[col] + "\"", inspected);
				}
			}
			assertEquals("final sheet", helper.getText(), grid.getGridText());
		}
	}
	
	public static class Checkpoint3
	{
		// Tests for checkpoint 3.
		// Note these must also pass for all subsequent checkpoints including final project.
		Grid grid;
		
		@Before
		public void initializeGrid()
		{
			grid = new Spreadsheet();
		}
		
		@Test
		public void testDateCell()
		{
			String date = "11/25/1964";
			grid.processCommand("A1 = " + date);
			Cell dateCell = grid.getCell(new TestLocation(0,0));
			assertEquals("date cell text", Helper.format(date), dateCell.abbreviatedCellText());
			assertEquals("date inspection text", date, dateCell.fullCellText());
		}
		
		@Test
		public void testBasicRealCell()
		{
			String real = "3.14";
			grid.processCommand("D18 = " + real);
			Cell realCell = grid.getCell(new TestLocation(17, 3));
			assertEquals("real cell text", Helper.format(real), realCell.abbreviatedCellText());
			assertEquals("real inspection text", real, realCell.fullCellText());
		}
		
		@Test
		public void testMoreRealCells()
		{
			String zero = "0";
			grid.processCommand("A1 = " + zero);
			Cell zeroCell = grid.getCell(new TestLocation(0, 0));
			assertEquals("real cell 0", Helper.format(zero), zeroCell.abbreviatedCellText());

			String negativeTwo = "-2";
			grid.processCommand("B1 = " + negativeTwo);
			Cell negativeTwoCell = grid.getCell(new TestLocation(0, 1));
			assertEquals("real cell -2", Helper.format(negativeTwo), negativeTwoCell.abbreviatedCellText());
			assertEquals("real inspection -2", negativeTwo, negativeTwoCell.fullCellText());
		}
		
		@Test
		public void testDifferentCellTypes()
		{
			grid.processCommand("H4 = 12/28/1998");
			grid.processCommand("G3 = \"5\"");
			grid.processCommand("F2 = -123.456");
			Cell dateCell = grid.getCell(new TestLocation(3, 7));
			Cell stringCell = grid.getCell(new TestLocation(2, 6));
			Cell realCell = grid.getCell(new TestLocation(1, 5));
			Cell emptyCell = grid.getCell(new TestLocation(0, 4));
			Cell[] differentCells = { dateCell, stringCell, realCell, emptyCell };
			for (int i = 0; i < differentCells.length - 1; i++)
				for (int j = i + 1; j < differentCells.length; j++)
				{
					assertTrue("date, string, real, empty cells must all have different class types",
							!differentCells[i].getClass().equals(differentCells[j].getClass()));
				}
		}
		
		@Test
		public void testFormulaAssignment()
		{
			for (int row = 1; row < 11; row++)
				for (int col = 1; col < 7; col++)
				{
					String cellName = Character.toString((char)('A' + col)) + (row + 1);
					grid.processCommand(cellName + " = 1");
				}
			String formula1 = "( 4 * 5.5 / 2 + 1 - -11.5 )";
			String formula2 = "( sUm B6-g11 )";
			String formula3 = "( AvG f8-F9 )";
			grid.processCommand("K9 = " + formula1);
			grid.processCommand("J10 = " + formula2);
			grid.processCommand("I11 = " + formula3);
			Cell cell1 = grid.getCell(new TestLocation(8, 10));
			Cell cell2 = grid.getCell(new TestLocation(9, 9));
			Cell cell3 = grid.getCell(new TestLocation(10, 8));
			assertEquals("cell length 1", 10, cell1.abbreviatedCellText().length());
			assertEquals("inspection 1", formula1, cell1.fullCellText());
			assertEquals("cell length 2", 10, cell2.abbreviatedCellText().length());
			assertEquals("inspection 2", formula2, cell2.fullCellText());
			assertEquals("cell length 3", 10, cell3.abbreviatedCellText().length());
			assertEquals("inspection 3", formula3, cell3.fullCellText());
		}

		@Test
		public void testProcessCommand()
		{
			Helper helper = new Helper();
			String first = grid.processCommand("A1 = 01/02/1822");
			helper.setItem(0, 0, "01/02/1822");
			assertEquals("grid with date", helper.getText(), first);
			String second = grid.processCommand("B2 = -5");
			helper.setItem(1, 1, "-5");
			assertEquals("grid with date and number", helper.getText(), second);
			String third = grid.processCommand("C3 = 2.718");
			helper.setItem(2, 2, "2.718");
			assertEquals("grid with date and two numbers", helper.getText(), third);
			String fourth = grid.processCommand("D4 = 0");
			helper.setItem(3, 3, "0");
			assertEquals("grid with date and three numbers", helper.getText(), fourth);
		}

	}
	
	public static class Checkpoint3Ex
	{
		// Extended tests for checkpoint 3, which must pass for final project.
		Grid grid;
		
		@Before
		public void initializeGrid()
		{
			grid = new Spreadsheet();
		}
		
		@Test
		public void testShortDateCell()
		{
			// NOTE spec not totally consistent on inspection format, allow either as entered or mm/dd/yyyy
			String[] datesEntered = { "1/2/1934", "7/15/2004", "11/6/2011" };
			String[] datesFormatted = { "01/02/1934", "07/15/2004", "11/06/2011" };
			Helper helper = new Helper();
			for (int col = 0; col < datesEntered.length; col++)
			{
				for (int row = 6; row < 20; row += 10)
				{
					String cellName = Character.toString((char)('a' + col)) + (row + 1);
					String sheet = grid.processCommand(cellName + " = " + datesEntered[col]);
					helper.setItem(row,  col, datesFormatted[col]);
					assertEquals("sheet after setting cell " + cellName, helper.getText(), sheet);
					String inspected = grid.getCell(new TestLocation(row, col)).fullCellText();
					assertTrue("inspected " + cellName + " either as entered or formatted",
							inspected.equals(datesEntered[col]) || inspected.equals(datesFormatted[col]));
				}
			}
			assertEquals("final sheet", helper.getText(), grid.getGridText());
		}
		
		@Test
		public void testRealCellFormat()
		{
			// NOTE spec not totally consistent on inspection format, allow anything that parses to within epsilon of as entered
			String[] realsEntered = { "3.00", "-74.05000", "400", "400.0" };
			String[] realsFormatted = { "3         ", "-74.05    ", "400       ", "400       " };
			Helper helper = new Helper();
			for (int col = 0; col < realsEntered.length; col++)
			{
				for (int row = 6; row < 20; row += 10)
				{
					String cellName = Character.toString((char)('A' + col)) + (row + 1);
					String sheet = grid.processCommand(cellName + " = " + realsEntered[col]);
					helper.setItem(row,  col, realsFormatted[col]);
					assertEquals("sheet after setting cell " + cellName, helper.getText(), sheet);
					String inspected = grid.getCell(new TestLocation(row, col)).fullCellText();
					double expected = Double.parseDouble(realsEntered[col]);
					double actual = Double.parseDouble(inspected);
					assertEquals("inspected real value", actual, expected, 1e-6);
				}
			}
			assertEquals("final sheet", helper.getText(), grid.getGridText());
		}

		@Test
		public void testRealCellTruncation()
		{
			String big = "-9876543212345";
			grid.processCommand("A1 = " + big);
			Cell bigCell = grid.getCell(new TestLocation(0, 0));
			assertEquals("real big cell length", 10, bigCell.abbreviatedCellText().length());
			assertEquals("real big inspection ", Double.parseDouble(big), Double.parseDouble(bigCell.fullCellText()), 1e-6);
			String precise = "3.14159265358979";
			grid.processCommand("A2 = " + precise);
			Cell preciseCell = grid.getCell(new TestLocation(1, 0));
			assertEquals("real precise cell length", 10, preciseCell.abbreviatedCellText().length());
			assertEquals("real precise cell", Double.parseDouble(precise), Double.parseDouble(preciseCell.abbreviatedCellText()), 1e-6);
			assertEquals("real precise inspection ", Double.parseDouble(precise), Double.parseDouble(preciseCell.fullCellText()), 1e-6);
			String moderate = "123456";
			grid.processCommand("A3 = " + moderate);
			Cell moderateCell = grid.getCell(new TestLocation(2, 0));
			assertEquals("real moderate cell length", 10, moderateCell.abbreviatedCellText().length());
			assertEquals("real moderate cell", moderate, moderateCell.abbreviatedCellText().trim());
			assertEquals("real moderate inspection", moderate, moderateCell.fullCellText());
		}
	}

	public static class Checkpoint4
	{
		// Tests for checkpoint 4.
		// Note these must also pass for all subsequent checkpoints including final project.
		Grid grid;
		
		@Before
		public void initializeGrid()
		{
			grid = new Spreadsheet();
		}
		
		@Test
		public void testConstant()
		{
			grid.processCommand("A1 = ( -43.5 )");
			Cell cell = grid.getCell(new TestLocation(0, 0));
			assertEquals("constant formula value", Helper.format("-43.5"), cell.abbreviatedCellText());
			assertEquals("constant formula inspection", "( -43.5 )", cell.fullCellText());
		}
		
		@Test
		public void testMultiplicative()
		{
			String formula = "( 2 * 3 * 4 * 5 / 2 / 3 / 2 )";
			grid.processCommand("A1 = " + formula);
			Cell cell = grid.getCell(new TestLocation(0, 0));
			assertEquals("multiplicative formula value", Helper.format("10"), cell.abbreviatedCellText());
			assertEquals("multiplicative formula inspection", formula, cell.fullCellText());
		}
		
		@Test
		public void testAdditive()
		{
			String formula = "( 1 + 3 + 5 - 2 - 4 - 6 )";
			grid.processCommand("L20 = " + formula);
			Cell cell = grid.getCell(new TestLocation(19, 11));
			assertEquals("additive formula value", Helper.format("-3"), cell.abbreviatedCellText());
			assertEquals("additive formula inspection", formula, cell.fullCellText());
		}
		
		@Test
		public void testMixed()
		{
			String formula = "( 5.4 * 3.5 / -1.4 + 27.4 - 11.182 )";
			grid.processCommand("E1 = " + formula);
			Cell cell = grid.getCell(new TestLocation(0, 4));
			assertEquals("mixed formula value length", 10, cell.abbreviatedCellText().length());
			assertEquals("mixed formula value", 2.718, Double.parseDouble(cell.abbreviatedCellText()), 1e-6);
			assertEquals("mixed formula inspection", formula, cell.fullCellText());
		}
		
		@Test
		public void testReferences()
		{
			String formula = "( A1 * A2 / A3 + A4 - A5 )";
			grid.processCommand("A1 = 5.4");
			grid.processCommand("A2 = 3.5");
			grid.processCommand("A3 = -1.4");
			grid.processCommand("A4 = 27.4");
			grid.processCommand("A5 = 11.182");
			grid.processCommand("L18 = " + formula);
			Cell cell = grid.getCell(new TestLocation(17, 11));
			assertEquals("reference formula value length", 10, cell.abbreviatedCellText().length());
			assertEquals("reference formula value", 2.718, Double.parseDouble(cell.abbreviatedCellText()), 1e-6);
			assertEquals("reference formula inspection", formula, cell.fullCellText());
			grid.processCommand("A4 = 25.4");
			assertEquals("updated value length", 10, cell.abbreviatedCellText().length());
			assertEquals("updated value", 0.718, Double.parseDouble(cell.abbreviatedCellText()), 1e-6);
			assertEquals("updated inspection", formula, cell.fullCellText());
		}
		
		@Test
		public void testTransitiveReferences()
		{
			grid.processCommand("F1 = 1");
			grid.processCommand("F2 = ( 1 )");
			grid.processCommand("F3 = ( F2 + F1 )");
			grid.processCommand("F4 = ( F2 + F3 )");
			grid.processCommand("F5 = ( F3 + F4 )");
			Cell cell = grid.getCell(new TestLocation(4, 5));
			assertEquals("Fib(5)", Helper.format("5"), cell.abbreviatedCellText());
			assertEquals("inspection", "( F3 + F4 )", cell.fullCellText());
		}
		
		@Test
		public void testProcessCommand()
		{
			Helper helper = new Helper();
			helper.setItem(0, 5,  "1");
			helper.setItem(1, 5, "1");
			helper.setItem(2,  5,  "2");
			helper.setItem(3,  5, "3");
			helper.setItem(4, 5, "5");
			grid.processCommand("F1 = 1");
			grid.processCommand("F2 = ( 1 )");
			grid.processCommand("F3 = ( F2 + F1 )");
			grid.processCommand("F4 = ( F2 + F3 )");
			String actual = grid.processCommand("F5 = ( F3 + F4 )");
			assertEquals("grid", helper.getText(), actual);
			String inspected = grid.processCommand("F4");
			assertEquals("inspected", "( F2 + F3 )", inspected);
			String updated = grid.processCommand("F3 = 11.5");
			helper.setItem(2, 5, "11.5");
			helper.setItem(3, 5, "12.5");
			helper.setItem(4, 5, "24");
			assertEquals("updated grid", helper.getText(), updated);
			String updatedInspected = grid.processCommand("F4");
			assertEquals("updated inspected", "( F2 + F3 )", updatedInspected);
		}
	}

	public static class Final
	{
		// Additional tests for final project.
		Grid grid;
		
		@Before
		public void initializeGrid()
		{
			grid = new Spreadsheet();
		}
		
		@Test
		public void testSumSingle()
		{
			grid.processCommand("A15 = 37.05");
			grid.processCommand("A16 = ( SuM A15-A15 )");
			Cell cell = grid.getCell(new TestLocation(15, 0));
			assertEquals("sum single cell", Helper.format("37.05"), cell.abbreviatedCellText());
		}
		
		@Test
		public void testAvgSingle()
		{
			grid.processCommand("A1 = -9");
			grid.processCommand("A2 = ( 3 * A1 )");
			grid.processCommand("B1 = ( avg A2-A2 )");
			Cell cell = grid.getCell(new TestLocation(0, 1));
			assertEquals("avg single cell", Helper.format("-27"), cell.abbreviatedCellText());
		}
		
		@Test
		public void testVertical()
		{
			grid.processCommand("C3 = 1");
			grid.processCommand("C4 = ( C3 * 2 )"); // 2
			grid.processCommand("C5 = ( C4 - C3 )"); // 1
			grid.processCommand("C6 = ( 32 - C4 )"); // 30
			grid.processCommand("K20 = ( SUM c3-c6 )"); // 34
			grid.processCommand("L20 = ( avg C3-C6 )"); // 8.5
			Cell cellSum = grid.getCell(new TestLocation(19, 10));
			Cell cellAvg = grid.getCell(new TestLocation(19, 11));
			assertEquals("sum vertical", Helper.format("34"), cellSum.abbreviatedCellText());
			assertEquals("avg vertical", Helper.format("8.5"), cellAvg.abbreviatedCellText());
		}
		
		@Test
		public void testHorizontal()
		{
			grid.processCommand("F8 = 3");
			grid.processCommand("G8 = ( 5 )");
			grid.processCommand("H8 = ( -1 * F8 + G8 )"); // 2
			grid.processCommand("I8 = ( sum F8-H8 )"); // 10
			grid.processCommand("J8 = ( AVG F8-I8 )"); // 5
			Cell cellSum = grid.getCell(new TestLocation(7, 8));
			Cell cellAvg = grid.getCell(new TestLocation(7, 9));
			assertEquals("sum horizontal", Helper.format("10"), cellSum.abbreviatedCellText());
			assertEquals("avg horizontal", Helper.format("5"), cellAvg.abbreviatedCellText());
		}
		
		@Test
		public void testRectangular()
		{
			for (int i = 0; i < 4; i++)
				for (int j = 0; j < 5; j++)
				{
					String cellId = "" + (char)('A' + j) + (i + 1);
					grid.processCommand(cellId + " = " + (i * j));
				}
			grid.processCommand("G8 = ( sum A1-E4 )");
			grid.processCommand("G9 = ( avg A1-E4 )");
			Cell cellSum = grid.getCell(new TestLocation(7, 6));
			Cell cellAvg = grid.getCell(new TestLocation(8, 6));
			assertEquals("sum rectangular", Helper.format("60"), cellSum.abbreviatedCellText());
			assertEquals("avg rectangular", Helper.format("3"), cellAvg.abbreviatedCellText());
		}
		
		@Test
		public void testProcessCommand()
		{
			Helper helper = new Helper();
			for (int i = 0; i < 4; i++)
				for (int j = 0; j < 5; j++)
				{
					String cellId = "" + (char)('A' + j) + (i + 1);
					grid.processCommand(cellId + " = " + (i * j));
					helper.setItem(i, j, "" + (i * j));
				}
			String first = grid.processCommand("G8 = ( sum A1-E4 )");
			helper.setItem(7, 6, "60");
			assertEquals("grid with sum", helper.getText(), first);
			String second = grid.processCommand("G9 = ( avg A1-E4 )");
			helper.setItem(8, 6, "3");
			assertEquals("grid with sum and avg", helper.getText(), second);
			String updated = grid.processCommand("E4 = ( sum A4-D4 )");
			helper.setItem(3, 4, "18");
			helper.setItem(7, 6, "66");
			helper.setItem(8, 6, "3.3");
			assertEquals("updated grid", helper.getText(), updated);
		}
	}
	

	public static class ExtraCreditOperationOrder
	{
		// Tests for operation order extra credit
		Grid grid;
		
		@Before
		public void initializeGrid()
		{
			grid = new Spreadsheet();
		}
		
		@Test
		public void testSimplePrecedence()
		{
			String formula = "( 1 + 2 * 3 )";
			grid.processCommand("A1 = " + formula);
			String result = grid.getCell(new TestLocation(0, 0)).abbreviatedCellText();
			assertEquals(formula, "7         ", result);
		}
		
		@Test
		public void testComplexPrecedence()
		{
			String formula = "( 1 - 3.0 / 5 + 7 / 2 - 4 * -18.5 + 1 )";
			grid.processCommand("L20 = " + formula);
			String result = grid.getCell(new TestLocation(19, 11)).abbreviatedCellText();
			assertEquals(formula, 78.9, Double.parseDouble(result), 1e-6);
		}
		
		@Test
		public void testReferencePrecedence()
		{
			String formulaA1 = "( 1 - 3 / -2 )";
			String formulaA2 = "( 4 * A1 / 2.5 - 3 / A1 )";
			String formulaA3 = "( A2 - A1 * 1.2 )";
			grid.processCommand("A1 = " + formulaA1);
			grid.processCommand("A2 = " + formulaA2);
			grid.processCommand("A3 = " + formulaA3);
			String result = grid.getCell(new TestLocation(2, 0)).abbreviatedCellText();
			assertEquals("formula with references and precedence", -0.2, Double.parseDouble(result), 1e-6);
		}
		
	}
	
	public static class ExtraCreditCommandErrors
	{
		// Tests for command errors extra credit
		Grid grid;
		
		@Before
		public void initializeGrid()
		{
			grid = new Spreadsheet();
		}
		
		@Test
		public void testInvalidCommand()
		{
			String before = grid.processCommand("A1 = \"thrang\"");
			String error = grid.processCommand("lesnerize");
			String after = grid.getGridText();
			assertTrue("error message starts with ERROR: ", error.startsWith("ERROR: "));
			assertEquals("grid contents unchanged", before, after);
		}
		
		@Test
		public void testInvalidCellAssignment()
		{
			String before = grid.processCommand("A1 = \"hello\"");
			String error1 = grid.processCommand("A37 = 5");
			String error2 = grid.processCommand("M1 = 3");
			String error3 = grid.processCommand("A-5 = 2");
			String error4 = grid.processCommand("A0 = 17");
			String after = grid.getGridText();
			assertTrue("error1 message starts with ERROR: ", error1.startsWith("ERROR: "));
			assertTrue("error2 message starts with ERROR: ", error2.startsWith("ERROR: "));
			assertTrue("error3 message starts with ERROR: ", error3.startsWith("ERROR: "));
			assertTrue("error4 message starts with ERROR: ", error4.startsWith("ERROR: "));
			assertEquals("grid contents unchanged", before, after);
		}
		
		@Test
		public void testInvalidConstants()
		{
			String before = grid.processCommand("A1 = \"hello\"");
			String error1 = grid.processCommand("A2 = 5...");
			String error2 = grid.processCommand("A3 = 4p");
			String error3 = grid.processCommand("A4 = \"he");
			String error4 = grid.processCommand("A5 = 1/2/aughtfour");
			String error5 = grid.processCommand("A6 = *9");
			String after = grid.getGridText();
			assertTrue("error1 message starts with ERROR: ", error1.startsWith("ERROR: "));
			assertTrue("error2 message starts with ERROR: ", error2.startsWith("ERROR: "));
			assertTrue("error3 message starts with ERROR: ", error3.startsWith("ERROR: "));
			assertTrue("error4 message starts with ERROR: ", error4.startsWith("ERROR: "));
			assertTrue("error5 message starts with ERROR: ", error5.startsWith("ERROR: "));
			assertEquals("grid contents unchanged", before, after);
		}
		
		@Test
		public void testInvalidFormulaAssignment()
		{
			grid.processCommand("A1 = 1");
			String before = grid.processCommand("A2 = 2");
			String error1 = grid.processCommand("A3 = 5 + 2");
			String error2 = grid.processCommand("A4 = ( avs A1-A2 )");
			String error3 = grid.processCommand("A5 = ( sum A0-A2 )");
			String error4 = grid.processCommand("A6 = ( 1 + 2");
			String error5 = grid.processCommand("A7 = ( avg A1-B )");
			String error6 = grid.processCommand("A8 = M80");
			String after = grid.getGridText();
			assertTrue("error1 message starts with ERROR: ", error1.startsWith("ERROR: "));
			assertTrue("error2 message starts with ERROR: ", error2.startsWith("ERROR: "));
			assertTrue("error3 message starts with ERROR: ", error3.startsWith("ERROR: "));
			assertTrue("error4 message starts with ERROR: ", error4.startsWith("ERROR: "));
			assertTrue("error5 message starts with ERROR: ", error5.startsWith("ERROR: "));
			assertTrue("error6 message starts with ERROR: ", error6.startsWith("ERROR: "));
			assertEquals("grid contents unchanged", before, after);
		}
		
		@Test
		public void testWhitespaceTolerance()
		{
			// OK to either treat as error or as valid, just don't crash
			String before = grid.getGridText();
			grid.processCommand("L20=5");
			grid.processCommand(" A1  =     -14 ");
			grid.processCommand("A1=-14");
			grid.processCommand("A1=(3+5*4/2)");
			grid.processCommand("A1=(sum L20-L20)");
			grid.processCommand("clear    A1");
			String after = grid.processCommand("clear");
			assertEquals("end with empty grid", before, after);
		}
	}
	
	public static class ExtraCreditEvaluationErrors
	{
		// Tests for evaluation errors extra credit
		private Grid grid;
		private final String expectedError = "#ERROR    ";
		
		@Before
		public void initializeGrid()
		{
			grid = new Spreadsheet();
		}
		
		@Test
		public void testSimpleError()
		{
			String formula = "( A2 )";
			grid.processCommand("A1 = " + formula);
			Cell cell = grid.getCell(new TestLocation(0, 0));
			assertEquals("evaluation error", expectedError, cell.abbreviatedCellText());
			assertEquals("formula", formula, cell.fullCellText());
		}
		
		@Test
		public void testDivideByZero()
		{
			String formula = "( 1 / 0 )";
			grid.processCommand("A1 = " + formula);
			Cell cell = grid.getCell(new TestLocation(0, 0));
			assertEquals("evaluation error", expectedError, cell.abbreviatedCellText());
			assertEquals("formula", formula, cell.fullCellText());
		}
		
		private void assertEvalError(int row, int col, String formula, String description)
		{
			Cell cell = grid.getCell(new TestLocation(row, col));
			assertEquals(description, expectedError, cell.abbreviatedCellText());
			assertEquals("formula", formula, cell.fullCellText());
		}
		
		private void assertEvalOK(int row, int col, String expected, String formula, String description)
		{
			Cell cell = grid.getCell(new TestLocation(row, col));
			assertEquals(description, expected, cell.abbreviatedCellText());
			assertEquals("formula", formula, cell.fullCellText());
		}
		
		@Test
		public void testSimpleTypeErrors()
		{
			String formula = "( avg A1-A1 )";
			grid.processCommand("A2 = " + formula);
			assertEvalError(1, 0, formula, "empty ref error");
			grid.processCommand("A1 = 1");
			assertEvalOK(1, 0, "1         ", formula, "valid ref");
			grid.processCommand("A1 = \"hello\"");
			assertEvalError(1, 0, formula, "string ref error");
			grid.processCommand("A1 = 2");
			assertEvalOK(1, 0, "2         ", formula, "valid ref");
			grid.processCommand("A1 = 11/20/2013");
			assertEvalError(1, 0, formula, "date ref error");
			grid.processCommand("A1 = 3");
			assertEvalOK(1, 0, "3         ", formula, "valid ref");
		}
		
		@Test
		public void testErrorPropagation()
		{
			String formulaA2 = "( sum A1-A1 )";
			String formulaA3 = "( 1 / A2 )";
			String formulaA4 = "( A3 + A3 )";
			String formulaB3 = "( A2 / 1 )";
			String formulaB4 = "( B3 + B3 )";
			String formulaC3 = "( avg A2-A3 )";
			String formulaC4 = "( sum C3-C3 )";
			grid.processCommand("A2 = " + formulaA2);
			grid.processCommand("A3 = " + formulaA3);
			grid.processCommand("A4 = " + formulaA4);
			grid.processCommand("B3 = " + formulaB3);
			grid.processCommand("B4 = " + formulaB4);
			grid.processCommand("C3 = " + formulaC3);
			grid.processCommand("C4 = " + formulaC4);
			assertEvalError(1, 0, formulaA2, "direct");
			assertEvalError(2, 0, formulaA3, "indirect");
			assertEvalError(3, 0, formulaA4, "indirect");
			assertEvalError(2, 1, formulaB3, "indirect");
			assertEvalError(3, 1, formulaB4, "indirect");
			assertEvalError(2, 2, formulaC3, "indirect");
			assertEvalError(3, 2, formulaC4, "indirect");
			grid.processCommand("A1 = 1");
			assertEvalOK(1, 0, "1         ", formulaA2, "direct");
			assertEvalOK(2, 0, "1         ", formulaA3, "indirect");
			assertEvalOK(3, 0, "2         ", formulaA4, "indirect");
			assertEvalOK(2, 1, "1         ", formulaB3, "indirect");
			assertEvalOK(3, 1, "2         ", formulaB4, "indirect");
			assertEvalOK(2, 2, "1         ", formulaC3, "indirect");
			assertEvalOK(3, 2, "1         ", formulaC4, "indirect");
			grid.processCommand("A1 = 0");
			assertEvalOK(1, 0, "0         ", formulaA2, "direct");
			assertEvalError(2, 0, formulaA3, "direct");
			assertEvalError(3, 0, formulaA4, "indirect");
			assertEvalOK(2, 1, "0         ", formulaB3, "indirect");
			assertEvalOK(3, 1, "0         ", formulaB4, "indirect");
			assertEvalError(2, 2, formulaC3, "indirect");
			assertEvalError(3, 2, formulaC4, "indirect");
		}
	}
	
	public static class ExtraCreditCircularReferenceErrors
	{
		// Tests for circular reference errors extra credit
		private Grid grid;
		private final String expectedError = "#ERROR    ";
		private final String expectedOne = "1         ";
		
		@Before
		public void initializeGrid()
		{
			grid = new Spreadsheet();
		}
		
		private void assertEvalError(int row, int col, String formula, String description)
		{
			Cell cell = grid.getCell(new TestLocation(row, col));
			assertEquals(description, expectedError, cell.abbreviatedCellText());
			assertEquals("formula", formula, cell.fullCellText());
		}
		
		private void assertEvalOK(int row, int col, String expected, String formula, String description)
		{
			Cell cell = grid.getCell(new TestLocation(row, col));
			assertEquals(description, expected, cell.abbreviatedCellText());
			assertEquals("formula", formula, cell.fullCellText());
		}

		@Test
		public void testCircularReference()
		{
			Helper thErrors = new Helper();
			Helper thOnes = new Helper();
			for (int col = 0; col < 3; col++)
			{
				thErrors.setItem(0,  col, expectedError);
				thOnes.setItem(0,  col,  expectedOne);
			}
			grid.processCommand("A1 = ( B1 )");
			grid.processCommand("b1 = ( c1 )");
			try
			{
				String gridErrors = grid.processCommand("C1 = ( a1 )");
				assertEquals("grid with circular reference errors", thErrors.getText(), gridErrors);
				String gridOnes = grid.processCommand("B1 = 1");
				assertEquals("grid with ones", thOnes.getText(), gridOnes);
				assertEvalOK(0, 2, expectedOne, "( a1 )", "noncircular");
				gridErrors = grid.processCommand("b1 = ( a1 )");
				assertEquals("second grid with circular reference errors", thErrors.getText(), gridErrors);
				assertEvalError(0, 2, "( a1 )", "circular");
			}
			catch (StackOverflowError e)
			{
				fail("Circular reference not handled, caught stack overflow error");
			}
		}
	}
}
