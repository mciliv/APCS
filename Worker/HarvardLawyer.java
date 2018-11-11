package worker;

public class HarvardLawyer extends Lawyer
{

	public HarvardLawyer(int initialYearsWorked)
	{
		super(initialYearsWorked);
	}
	
	public double getSalary()
	{
		return super.getSalary() * 1.2;
	}
	
	
	public int getVacationDays()
	{
		return super.getVacationDays() + 3;
	}
	
	public String getVacationForm()
	{
		return super.getVacationForm() + super.getVacationForm() + super.getVacationForm() + super.getVacationForm();
	}
	
	public void sue()
	{
		System.out.println("Consider yourself sued");
	}
}
