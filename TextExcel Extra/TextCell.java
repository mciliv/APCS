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
		if (string.length()>=10)
		{
		return string.substring(0,10);
		} else
		{
			String abbreviatedString = string;
			for (int i = 10; i>string.length(); i--)
			{
				abbreviatedString += " ";
			}
			return abbreviatedString;
		}
		
	}
	public String fullCellText() // text for individual cell inspection, not truncated or padded
	{
		return "\"" + string + "\"";
	}
}
