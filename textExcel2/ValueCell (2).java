package textExcel;

public class ValueCell extends RealCell
{
	public ValueCell(String mathString)
	{
		super(mathString);
	}
	
	public String abbreviatedCellText() // text for spreadsheet cell display, must be exactly length 10
	{
		super.getMathString();
		if ((super.getMathString()).length()>=10)
		{
		return (super.getMathString()).substring(0,10);
		} 
			String abbreviatedString = (super.getMathString());
			for (int i = 10; i>(super.getMathString()).length(); i--)
			{
				abbreviatedString += " ";
			}
			return abbreviatedString;
	
	}
	
	public String fullCellText() // text for individual cell inspection, not truncated or padded
	{
		String theMathString = super.getMathString();
		return theMathString;
	}
	
	public double getDoubleValue()
	{
		return Double.parseDouble(super.getMathString());
	}
}
