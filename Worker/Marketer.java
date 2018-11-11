package worker;

public class Marketer extends Employee
{
	public Marketer(int initialYearsWorked)
	{
		super(initialYearsWorked);
	}
	
	public double getSalary()
	{
		return 50000.0;
	}
	
	public void advertise()
	{
		System.out.println("Act now, while supplies last");
	}
}
