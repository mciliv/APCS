package ticket;

public class TicketMain
{

	public static void main(String[] args) 
	{

		AdvanceTicket ticket12 = new AdvanceTicket(12,8);
		AdvanceTicket ticket14 = new AdvanceTicket(14,100);
		StudentAdvanceTicket ticket15 = new StudentAdvanceTicket(15,3);	
		StudentAdvanceTicket ticket20 = new StudentAdvanceTicket(20,11);
		WalkupTicket ticket28 = new WalkupTicket(28);

		System.out.println(ticket12);
		System.out.println(ticket14);
		System.out.println(ticket15);
		System.out.println(ticket20);
		System.out.println(ticket28);
	}
}
