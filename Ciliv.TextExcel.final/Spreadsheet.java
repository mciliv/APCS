package textExcel;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Spreadsheet implements Grid
{
	private Cell[][] spreadsheet;

	public Spreadsheet()
	{
		spreadsheet = new Cell[20][12];

		for (int i=0; i<spreadsheet.length; i++)
		{
			for (int j=0; j<spreadsheet[i].length; j++)
			{
				spreadsheet[i][j] = new EmptyCell();
			}
		}
	}

	@Override
	public String processCommand(String command)
	{
		if(command.equals(""))
		{
			return "";
		} else
		{
			if (command.indexOf(" ")==-1)
			{

				if (command.equalsIgnoreCase("clear"))
				{
					//Clears Whole Sheet
					for (int i=0; i<spreadsheet.length; i++)
					{
						for (int j=0; j<spreadsheet[i].length; j++)
						{
							spreadsheet[i][j] = new EmptyCell();
						}
					}

				} else
				{
					// Returns the Cell
					SpreadsheetLocation theLocation1 = new SpreadsheetLocation(command);
					return spreadsheet[theLocation1.getRow()][theLocation1.getCol()].fullCellText();
				}

			} else
			{
				if (command.substring(0,5).equalsIgnoreCase("clear"))//fix ==
				{
					String cellName = command.substring(command.indexOf(" ")+1, command.length());
					SpreadsheetLocation theLocation3 = new SpreadsheetLocation(cellName);

					//EmptyCell
					spreadsheet[theLocation3.getRow()][theLocation3.getCol()] = new EmptyCell();

				} else
				{
					String[] text = command.split(" ", 3); //Splits String

					String cellName = text[0];
					String theText = text[2];
					SpreadsheetLocation theLocation2 = new SpreadsheetLocation(cellName);

					char first = (theText.charAt(0));
					char last = (theText.charAt(theText.length()-1));

					if (first=='"' &&last=='"')
					{
						theText = theText.substring(1, theText.length()-1);


						spreadsheet[theLocation2.getRow()][theLocation2.getCol()] = new TextCell(theText);
					} else
					{

						SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
						Date date = dateFormat.parse(theText, new ParsePosition(0));

						if (date==null)
						{
							char firstp = (theText.charAt(0));
							char lastp = (theText.charAt(theText.length()-1));

							if (first=='(' &&last==')')
							{
								//FormulaCell
								spreadsheet[theLocation2.getRow()][theLocation2.getCol()] = new FormulaCell(theText, spreadsheet);
							} else
							{
								//ValueCell
								spreadsheet[theLocation2.getRow()][theLocation2.getCol()] = new ValueCell(theText);
							}


						} else
						{
							//DateCell
							spreadsheet[theLocation2.getRow()][theLocation2.getCol()] = new DateCell(theText);
						}
					}
				}
			}
			return getGridText();
		}
	}

	public int theRows(char letter) //gets the corresponding number
	{
		int letternum = letter - 65;
		return letternum;
	}

	@Override
	public int getRows()
	{
		return 20;
	}

	@Override
	public int getCols()
	{
		return 12;
	}

	@Override
	public Cell getCell(Location loc)
	{
		return spreadsheet[loc.getRow()][loc.getCol()];
	}

	@Override
	public String getGridText()
	{
		String grid = "   |";

		for (int a = 0; a<12; a++)
		{
			int letter = 'A' + a;
			char letters = (char) letter;
			grid += letters + "         |";
		}
		
		grid += "\n";
		for (int i=0; i<spreadsheet.length; i++)
		{
			grid+= i + 1;
			if (i<9)
			{
				grid +="  |";
			} else
			{
				grid += " |";
			}
			for (int j=0; j<spreadsheet[i].length; j++)
			{
				grid += spreadsheet[i][j].abbreviatedCellText() + "|";
			}
			grid += "\n";
		}
		return grid;
	}
}
