package worker;

public class Employee implements Worker
{
	private int yearsWorked;
	
	public Employee(int initialYearsWorked)
	{
		yearsWorked = initialYearsWorked;
	}
	
	public int getHours()
	{
		return 40;
	}
	
	public double getSalary()
	{
		return 40000.0;
	}
	
	public int getVacationDays()
	{
		return 10 + 2*yearsWorked;
	}
	
	public String getVacationForm()
	{
		return "yellow";
	}
	
}
