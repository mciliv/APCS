package textExcel;

import java.util.Arrays;

public class FormulaCell extends RealCell
{
	private Grid grid;

	public FormulaCell(String mathString, Grid grid)
	{
		super(mathString);
		this.grid = grid;
	}
	
	public double getDoubleValue()
	{
	
		String theMathString = getMathString();

		theMathString = theMathString.substring(2, theMathString.length()-2);

		String[] formula = theMathString.split(" ");


		int theNumber = 0;

		int counter = 1;

		Double[] numbers = new Double[formula.length/2+1];
		for (int i = 0; i<formula.length; i += 2)
		{
			
			//			if (i/2==0)
			//			{
			System.out.println("This is the formula at i");
			System.out.println(formula[i]);
			
			
			
			theNumber = Character.toUpperCase(formula[i].charAt(0)) - 'A';
			if (theNumber>=0||theNumber<=11)
			{
				System.out.println("Printing formula of i again " + formula[i]);
				SpreadsheetLocation theReplacement = new SpreadsheetLocation(formula[i]);


				// double returned = spreadsheet[theReplacement.getRow()][theReplacement.getCol()].getDoubleValue;
				RealCell returned = (RealCell) grid.getCell(theReplacement);

				formula[i] = returned.getDoubleValue() + "";

			}
			numbers[counter] =  Double.parseDouble(formula[i]);

			counter++;



		} //else
		//			{
		//				if (formula[i]=="+")
		//				{
		//					value = (double) formula[i-1];
		//				}
		//			}


		double value = numbers[0];
		for (int j = 1; j<formula.length; j += 2)
		{
			System.out.println("The array" + Arrays.deepToString(numbers));
			System.out.println("Printing j+1" + numbers[j+1]);
			switch(formula[j])
			{

	
			case "+": value += numbers[j/2+1];
			break;	

			case "-" : value -= numbers[j+1];
			break;

			case "*": value *= numbers[j+1];
			break;

			case "/" : value /= numbers[j+1];
			break;

			// can you do if statement within variable?
			}
		}
		return value;
	}

	public String abbreviatedCellText() // text for spreadsheet cell display, must be exactly length 10
	{
		String text = getDoubleValue() + "";

		if (text.length()>=10)
		{
			return (text.substring(0,10));
		} 
		String abbreviatedString = (text);
		for (int i = 10; i>text.length(); i--)
		{
			abbreviatedString += " ";
		}
		return abbreviatedString;
	}
	
	public String fullCellText() // text for individual cell inspection, not truncated or padded
	{
		String text = getDoubleValue() + "";
		return text;
	}

	
}
