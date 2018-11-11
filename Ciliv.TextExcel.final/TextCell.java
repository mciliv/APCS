package textExcel;

public class TextCell implements Cell
{
	private String string;
	
	public TextCell(String string1)
	{
		string = string1;
	}
	
	public String abbreviatedCellText() // text for spreadsheet cell display, must be exactly length 10
	{
		String theString = string;

		return Chop.chopper(theString);
		
	}
	public String fullCellText() // text for individual cell inspection, not truncated or padded
	{
		return "\"" + string + "\"";
	}
}
