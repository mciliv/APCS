package worker;

public class Runner 
{
	
	
	public static void main(String[] args)
	{
		Worker worker1 = getWorker();
		Worker worker2 = getHarvardLawyer();
		System.out.print(worker1.getHours() + "\n" + worker1.getSalary()
+ "\n" + worker1.getVacationDays() + "\n"+ worker1.getVacationForm() + "\n");
		
		System.out.println(worker2.getHours() + "\n" + worker2.getSalary()
				+ "\n" + worker2.getVacationDays() + "\n"+ worker2.getVacationForm());
	}
	
	public static Worker getWorker()
	{
		Employee Employee1 = new Employee(8);
		return Employee1;
		
		
		
	}
	
public static Worker getHarvardLawyer()
{
	HarvardLawyer Lawyer1 = new HarvardLawyer(10);
	return Lawyer1;
}
}
