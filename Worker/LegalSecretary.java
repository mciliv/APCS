package worker;

public class LegalSecretary extends Secretary
{
	public LegalSecretary(int initialYearsWorked)
	{
		super(initialYearsWorked);
	}
	
	public void fileLegalBrief()
	{
		System.out.println("Legal Brief");
	}
}
