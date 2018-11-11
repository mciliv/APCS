package worker;

public class Janitor extends Employee //Exercise 2
{
	public Janitor(int initialYearsWorked)
	{
		super(initialYearsWorked);
	}
	
	public int getHours()
	{
		return super.getHours() * 2;
	}
	public double getSalary()
	{
		return super.getSalary() - 10000.0;
	}
	
	public int getVacationDays()
	{
		return super.getVacationDays() / 2 + super.getVacationDays() % 2; 
		//added "Super.getVacationDays() % 2" so that it's rounded up
	}
	public String clean()
	{
		return "Workin' for the man.";
	}
}
