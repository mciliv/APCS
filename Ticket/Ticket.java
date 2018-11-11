package ticket;

public class Ticket 
{
	private int number;
	

	public Ticket(int theNumber)
	{
		number = theNumber;
	}
	
	public double getPrice()
	{
		return 0.0;
	}

	public String toString()
	{
		return "Number: " + number + ", Price: " + getPrice();
	}

}
