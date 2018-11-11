package textExcel;

public class Chop 
{
public static String chopper(String theString)
{
	if (theString.length()>=10)
	{
	return theString.substring(0,10);
	} else
	{
		String abbreviatedString = theString;
		for (int i = 10; i>theString.length(); i--)
		{
			abbreviatedString += " ";
		}
		return abbreviatedString;
	}
}
}
