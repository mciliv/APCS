package bank;

public class MinMaxAccount extends BankAccount
{
	private int balance;
	private int minBalance;
	private int maxBalance;
	
	public MinMaxAccount(Startup s)
	{
		super(s);
		balance = s;
	minBalance = s;	
	maxBalance = s;
	
	}

	public void debit(Debit d)
	{
		balance += d;
		if (balance >= maxBalance) //getBalance ?
		{
		maxBalance = balance;
		}
//		super.debit(d);
	}
	
	public void credit(credit c)
	{
		balance -= c;
		if (balance >= minBalance) //getBalance ?
		{
		minBalance = balance;
		}
//		super.debit(c);
	}
	
	public int getBalance()
	{
		return super.getBalance();
	}
	
	public int getMin()
	{
		return minBalance;
	}

	public int getMax()
	{
		return maxBalance;
	}
}
