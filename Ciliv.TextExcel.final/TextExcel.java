package textExcel;

import java.util.Scanner;

public class TextExcel
{
	public static void main(String[] args)
	{
		Grid sheet = new Spreadsheet(); // Keep this as the first statement in main
		Scanner console = new Scanner(System.in);
		System.out.println(sheet.getGridText());

		String input = console.nextLine();
		input = console.nextLine();

		while(!input.equalsIgnoreCase("Quit"))
		{
			System.out.println(sheet.processCommand(input));
			input = console.nextLine();
		}
	}
}
