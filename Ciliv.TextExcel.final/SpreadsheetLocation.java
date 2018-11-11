package textExcel;

public class SpreadsheetLocation implements Location
{
	private int rows;
	private int columns;
	
	public SpreadsheetLocation(String cellName)
	{
		char letter = Character.toUpperCase(cellName.charAt(0));
		columns = (int) (letter - 65);
		rows = Integer.parseInt(cellName.substring(1,cellName.length()))-1;
	}
	// represents a location like B6, must be implemented by your SpreadsheetLocation class
		public int getRow() // gets row of this location
		{
			return rows;
		}
		public int getCol() // gets column of this location
		{
			return columns;
		}
}
