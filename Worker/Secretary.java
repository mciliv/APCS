package worker;

public class Secretary extends Employee
{
	public Secretary(int initialYearsWorked)
	{
		super(initialYearsWorked);
	}
	
	public void takeDication(String words)
	{
		System.out.println(words);
	}
}
