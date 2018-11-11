package textExcel;

// Update this file with your own code.
import java.util.Scanner;

public class TextExcel
{
	//Notes: How do you read the tests to understand what makes what
	

	public static void main(String[] args)
	{
		
		
		Grid sheet = new Spreadsheet(); // Keep this as the first statement in main
		Scanner console = new Scanner(System.in);
		System.out.println(sheet.getGridText());
		String input = console.nextLine();
		
		
	
		
		while(!input.equalsIgnoreCase("Quit"))
		{
//			try
//			{
			System.out.println(sheet.processCommand(input));
//			}
//			catch (Throwable t)
//			{
//				System.out.println("#ERROR");
//			}
			
			
			input = console.nextLine();
		}
		// TODO finish implementing main by adding your own code here
	}
}
