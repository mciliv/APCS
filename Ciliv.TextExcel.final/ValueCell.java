package textExcel;

public class ValueCell extends RealCell
{
	public ValueCell(String mathString)
	{
		super(mathString);
	}

	public String abbreviatedCellText() // text for spreadsheet cell display, must be exactly length 10
	{
		String newText = fullCellText();
	
		return Chop.chopper(newText);
	}

	public String fullCellText() // text for individual cell inspection, not truncated or padded
	{
		String theMathString = "" + super.getDoubleValue();

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
		return super.getDoubleValue();
	}
}