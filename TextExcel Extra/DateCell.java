package textExcel;

public class DateCell implements Cell
{
	private String dateString;
	
	public DateCell(String theDateString)
	{
		dateString = theDateString;
	}
	
	public String theNewDateString()
	{
		
		
		String[] numbers = dateString.split("/");
		if (numbers[0].length()==1)
		{
			numbers[0] = "0" + numbers[0];
		}
		if (numbers[1].length()==1)
		{
			numbers[1] = "0" + numbers[1];
		}
		
		
		
		return numbers[0] + "/" + numbers[1] + "/" + numbers[2];
	}
	
	public String abbreviatedCellText() // text for spreadsheet cell display, must be exactly length 10
	{
		String theNewDateString = theNewDateString();
		if (theNewDateString.length()>=10)
		{
		return theNewDateString.substring(0,10);
		} else
		{
			String abbreviatedString = theNewDateString;
			for (int i = 10; i>theNewDateString.length(); i--)
			{
				abbreviatedString += " ";
			}
			return abbreviatedString;
		}
		
	}
	public String fullCellText() // text for individual cell inspection, not truncated or padded
	{
		return theNewDateString();
	}
}