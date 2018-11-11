package textExcel;

public class DateCell implements Cell
{
	private String dateString;
	
	public DateCell(String theDateString)
	{
		dateString = theDateString;
	}
	
	public String abbreviatedCellText() // text for spreadsheet cell display, must be exactly length 10
	{
		if (dateString.length()>=10)
		{
		return dateString.substring(0,10);
		} else
		{
			String abbreviatedString = dateString;
			for (int i = 10; i>dateString.length(); i--)
			{
				abbreviatedString += " ";
			}
			return abbreviatedString;
		}
		
	}
	public String fullCellText() // text for individual cell inspection, not truncated or padded
	{
		return dateString;
	}
}
