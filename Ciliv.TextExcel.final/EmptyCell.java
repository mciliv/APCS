package textExcel;

public class EmptyCell implements Cell //All cells initialized to this
{
	public EmptyCell()
	{
		
	}

	public String abbreviatedCellText()// text for spreadsheet cell display, must be exactly length 10
	{
		return "          ";
	}
	public String fullCellText() // text for individual cell inspection, not truncated or padded
	{
		return "";
	}
}
