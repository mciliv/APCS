package textExcel;

import java.util.Arrays;

public class FormulaCell extends RealCell
{

	private Cell[][] spreadsheet; //Same spreadsheet

	public FormulaCell(String mathString, Cell[][] spreadsheet)
	{
		super(mathString);
		this.spreadsheet = spreadsheet;
	}

	public String abbreviatedCellText() // text for spreadsheet cell display, must be exactly length 10
	{
		String theString = shortenedText() + "";
		
		return Chop.chopper(theString);
	}
	public String fullCellText() // text for individual cell inspection, not truncated or padded
	{

		return getMathString();

	}

	public String shortenedText()
	{
		String theMathString = getDoubleValue() + "";

		while (theMathString.indexOf(".")!=-1 && theMathString.charAt(theMathString.length()-1)==('0'))
		{
			theMathString = theMathString.substring(0, theMathString.length()-1);
		}
		if (theMathString.charAt(theMathString.length()-1)=='.')
		{
			theMathString = theMathString.substring(0, theMathString.length()-1);
		}

		return theMathString;
	}

	public double getDoubleValue()
	{		
		String theMathString = getMathString();

		theMathString = theMathString.substring(2, theMathString.length()-2);

		String[] formula = theMathString.split(" ");

		if (formula[0].equalsIgnoreCase("sUm")||formula[0].equalsIgnoreCase("AvG"))
		{
			String[] cellNames = (formula[1].split("-"));
			SpreadsheetLocation theFirst = new SpreadsheetLocation(cellNames[0]);
			SpreadsheetLocation theLast = new SpreadsheetLocation(cellNames[1]);

			String letter = "";
			int number = 0;
			double value = 0.0;
			for (int i = theFirst.getRow(); i<=theFirst.getRow() + (theLast.getRow()-theFirst.getRow()); i++)
			{
				for (int j = theFirst.getCol(); j<=theFirst.getCol() + (theLast.getCol()-theFirst.getCol()); j++)
				{
					value += ((RealCell) spreadsheet[i][j]).getDoubleValue();
				}
			}
			if(formula[0].equalsIgnoreCase("AvG"))
			{
				value /= (theLast.getRow()-theFirst.getRow() + 1) * (theLast.getCol()-theFirst.getCol() + 1);
			}
			return value;
		}

		for (int i = 0; i<formula.length; i += 2)
		{
			int theChecker = Character.toUpperCase(formula[i].charAt(0)) - 'A';
			if (theChecker>=0 && theChecker<=11)
			{
				SpreadsheetLocation theReplacement = new SpreadsheetLocation(formula[i]);

				double returned = ((RealCell) spreadsheet[theReplacement.getRow()][theReplacement.getCol()]).getDoubleValue();

				formula[i] = returned + "";

			}
		}

		int theNumber = 0;
		int counter = 0;
		Double[] numbers = new Double[formula.length/2+1];
		for (int i = 0; i<formula.length; i += 2)
		{
			numbers[counter] =  Double.parseDouble(formula[i]);

			counter++;
		}


		counter = 1;
		double value = numbers[0];
		for (int j = 1; j<formula.length; j += 2)
		{
			//		USEFUL:	System.out.println("The array" + Arrays.deepToString(numbers));
			if (formula[j].equals("+"))
			{
				value += numbers[counter];
			}else if (formula[j].equals("-"))
				{
					value -= numbers[counter];

				} else if (formula[j].equals("*"))
					{
						value *= numbers[counter];
					}
					else
					{
						value /= numbers[counter];

					}
			counter++;

		}
		return value;

	}
}



//counter = 0;
//double value = numbers[0];
//
//int numberOfFirstOperands = 0;
//
//
//
//for (int i = 1; i<formula.length; i += 2)
//{
//	if (formula[i].equals("+")||formula[i].equals("-"))
//	{
//		numberOfFirstOperands++;
//	}
//}
//
//int opoCounter = 0;
//
//String[] Operands1 = new String[numberOfFirstOperands];
//
//
//for (int i = 1; i<formula.length; i += 2)
//{
//	if (formula[i].equals("+")||formula[i].equals("-"))
//	{
//		Operands1[opoCounter] = formula[i];
//		opoCounter++;
//	}
//
//}
//
//
//
//
//int numberOfSecondOperands = 0;
//
//for (int i = 1; i<formula.length; i += 2)
//{
//	if (formula[i].equals("*")||formula[i].equals("/"))
//	{
//		numberOfSecondOperands++;
//	}
//
//}
//
//
//Double[] newNumbers1 = new Double[numberOfFirstOperands + 1];
//int supercounter = 0;
//int counter1 = 0;
//int counter2 = 0;
//int counterO = 0;
//value = 1;
//for (int i = 1; i<formula.length; i += 2)
//{
//	if (i!=1 && i<(formula.length-2) && (formula[i+2].equals("*")||formula[i+2].equals("/")))
//	{
//
//		if (formula[i].equals("*"))
//		{
//			numbers[counter1 + 1] = numbers[counter]*numbers[counter+1];
//
//		}
//		else
//		{
//			//				 if (formula[i].equals("/"))
//			//				{
//			numbers[counter1+1] = numbers[counter1] / numbers[counter1 + 1];
//
//			//				}
//
//		}
//		counter2++;
//	}
//	else
//	{
//		if (formula[i].equals("*"))
//		{
//			newNumbers1[supercounter] = numbers[counter-counter2 + counter1] * numbers[counter-counter2 + counter1 + 1];
//			counter1++;
//			if (i<formula.length-4)
//			{
//				i+=2;
//			}
//		}
//		else
//		{
//			if (formula[i].equals("/"))
//			{
//				newNumbers1[supercounter] = numbers[counter-counter2 + counter1] / numbers[counter-counter2 + counter1 +1];
//				counter1++;
//				i+=2;
//			} else 
//			{
//
//				newNumbers1[supercounter] = numbers[supercounter + counter1];
//				counterO++;
//
//			}
//
//		}
//	}
//	counter++;
//	supercounter = counterO + counter1;
//}
////TODO: USE OPERANDS1
//
//counter = 0;
//value = newNumbers1[0];
//
//if (newNumbers1.length != 1)
//{
//
//	for (int i = 0; i< Operands1.length; i++)
//	{
//		if (Operands1[i].equals("+"))
//		{
//			value += newNumbers1[i+1];
//		}else
//		{					
//			value -= newNumbers1[i+1];
//		}
//	}
//
//}
//
//
//else 
//{
//	//			for (int j = 1; j<newNumbers1.length; j += 2)
//	//			{
//	//				//			System.out.println("The array" + Arrays.deepToString(numbers));
//	//
//	//
//	//				if (formula[j].equals("+")||formula[j].equals("-"))
//	//				{
//	//
//	//					counter++;
//	//
//	//					if (formula[j].equals("+"))
//	//					{
//	//						value += newNumbers1[counter];
//	//					}else
//	//					{					
//	//						value -= newNumbers1[counter];
//	//					}
//	//				}
//	//			}
//
//}
//return value;
//}
//}

//Code that passed 2 of the Order of Operations tests


///*
//counter = 1;
//double value = numbers[0];
//
//int numberOfFirstOperands = 0;
//
//for (int i = 1; i<formula.length; i += 2)
//{
//if (formula[i].equals("*")||formula[i].equals("/"))
//{
//numberOfFirstOperands++;
//}
//
//}
//
//String[] Operands1 = new String[formula.length/2 - numberOfFirstOperands];
//
//
//Double[] newNumbers1 = new Double[numbers.length-numberOfFirstOperands];
//
//int counter1 = 0;
//int counterO = 0;
//value = 1;
//for (int i = 1; i<formula.length; i += 2)
//{
//if (i!=(formula.length-1) && ((formula[i].equals("*")||formula[i].equals("/")) && (formula[i+2].equals("*")||formula[i+2].equals("/"))))
//{
//if (formula[i].equals("*"))
//{
//	numbers[i+1] = numbers[i-1]*numbers[i+1];
//
//}
//else
//{
//	//				 if (formula[i].equals("/"))
//	//				{
//	numbers[i+1] = numbers[i-1] / numbers[i+1];
//
//	//				}
//
//}
//
//
//if (formula[i].equals("*"))
//{
//	newNumbers1[counter] = numbers[i-1]*numbers[i+1];
//	counter1++;
//}
//else
//{
//	if (formula[i].equals("/"))
//	{
//		newNumbers1[counter] = numbers[i-1] / numbers[i+1];
//		counter1++;
//	} else 
//	{
//		Operands1[counter] = formula[i];
//		counterO++;
//
//	}
//}
//}
//
//counter = 0;
//value = 0;
//if (newNumbers1.length == 1)
//{
//value = newNumbers1[0];
//}
//else 
//{
//for (int j = 1; j<newNumbers1.length; j += 2)
//{
//	//			System.out.println("The array" + Arrays.deepToString(numbers));
//
//
//	if (formula[j].equals("+")||formula[j].equals("-"))
//	{
//
//		counter++;
//
//		if (formula[j].equals("+"))
//		{
//			value += numbers[counter];
//		}else
//		{					
//			value -= numbers[counter];
//		}
//	}
//}
//
//}
//
//
//
//}
//return value;
//}
//}

