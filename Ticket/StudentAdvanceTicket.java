package ticket;

public class StudentAdvanceTicket extends AdvanceTicket
{

	public StudentAdvanceTicket(int theNumber, int theDaysBeforePurchase)
	{
		super(theNumber, theDaysBeforePurchase);
	}
	
	public double getPrice()
	{
			return super.getPrice()/2;
		//Assuming will round down, only considered if price ever were to become odd
	}
	
	public String toString()
	{
		return super.toString() + " (ID required)";
	}

}
