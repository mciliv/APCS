package textExcel;

public abstract class RealCell implements Cell
{
	private String mathString;
	
	public RealCell(String mathString)
	{
		this.mathString = mathString;
	}
	
	public String getMathString()
	{
		return mathString;
	}
	
	public String abbreviatedCellText() // text for spreadsheet cell display, must be exactly length 10
	{
		return "         ";
	}
	public String fullCellText() // text for individual cell inspection, not truncated or padded
	{
		return "";
	}
	
	public double getDoubleValue()
	{
		return 0.0;
	}
}
