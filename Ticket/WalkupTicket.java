package ticket;

public class WalkupTicket extends Ticket
{
	public WalkupTicket(int theNumber)
	{
		super(theNumber);
	}
	
	public double getPrice()
	{
		return 50.0;
	}
	
	
}
