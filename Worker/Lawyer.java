package worker;

public class Lawyer extends Employee
{
	
	public Lawyer(int initialYearsWorked)
	{
		super(initialYearsWorked);
	}
	
	public int getVacationDays()
	{
		return super.getVacationDays() + 5;
	}
	
	public String getVacationForm()
	{
		return "pink";
	}
	
	public void sue()
	{
		System.out.println("Consider yourself sued");
	}
}

