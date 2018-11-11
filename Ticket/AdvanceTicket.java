package ticket;

public class AdvanceTicket extends Ticket
{
	private int daysBeforePurchase;
	public AdvanceTicket(int theNumber, int daysBeforePurchase)
	{
		
		super(theNumber);
		this.daysBeforePurchase=daysBeforePurchase;
	}
	
	public int getDaysBeforePurchase()
	{
		return daysBeforePurchase;
	}
	
	public double getPrice()
	{
		
		if (getDaysBeforePurchase() >= 10)
		{
			return 30.0;
		}
		return 40.0;
	}
	
//	public String toString()
//	{
//		return super.getPrice() + "";
//	}
}
