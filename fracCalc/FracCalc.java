package fracCalc;

import java.util.Scanner; 

public class FracCalc {

	public static void main(String[] args)  
	{ 
		Scanner calculator = new Scanner(System.in); 
		System.out.println("Welcome to the FRACTION CALCULATOR!");
		System.out.println("Please enter an equation to calculate or type quit to quit."); 
		String Input = calculator.nextLine(); 
		while (!Input.equalsIgnoreCase("quit")) 
		{
			System.out.println(produceAnswer(Input));
			Input = calculator.nextLine(); 
		}
		System.out.println("Thank you for using the calculator!");
	} 

	public static String produceAnswer(String Input) 
	{  
		String Answer = "1";
		String again = "yes";
		while (again.equals("yes"))
		{
			int space = Input.indexOf(" ");
			int space2 = Input.indexOf(" ", space + 1);
			if (space==-1||space2==-1)
			{
				return "ERROR: Input is in an invalid format.";
			}

			String firstnumber = Input.substring(0, space); 
			String secondnumber = Input.substring(space + 3, Input.length()); 
			String operation = Input.substring(space + 1, space + 2);

			if (Input.indexOf(" ", space + 3)==-1)
			{
				firstnumber = Input.substring(0, space); 
				secondnumber = Input.substring(space + 3, Input.length()); 
				operation = Input.substring(space + 1, space + 2);
				again = "no";

			} else
			{
				String toomanyspaces = Input.charAt(Input.length()-1) + "";
				if (toomanyspaces.equals(" "))
				{
					return "ERROR: Input is in an invalid format.";
				}

				firstnumber = Input.substring(0, space); 
				String secondspace2 = Input.charAt(space2 + 1) + "";
				if (secondspace2.equals(" "))
				{
					return "ERROR: Input is in an invalid format.";
				}
				secondnumber = Input.substring(space + 3, Input.indexOf(" ", space + 3)); 

				operation = Input.substring(space + 1, space + 2);
				again = "yes";
			}

			if (!(operation.equals("+")||operation.equals("-")||operation.equals("*")||operation.equals("/")))
			{
				return "ERROR: Input is in an invalid format.";
			}

			String convertednum1 = Converter(firstnumber); 
			String convertednum2 = Converter(secondnumber);

			if (convertednum1.equals("no")||convertednum2.equals("no"))
			{
				return "ERROR: Input is in an invalid format.";
			}


			String numeratorcn1 = convertednum1.substring(0, convertednum1.indexOf("/"));
			String denomenatorcn1 = convertednum1.substring(convertednum1.indexOf("/") +1, convertednum1.length());

			int numeratorcn1int = Integer.parseInt(numeratorcn1); 
			int denomenatorcn1int = Integer.parseInt(denomenatorcn1); 

			String numeratorcn2 = convertednum2.substring(0, convertednum2.indexOf("/"));
			String denomenatorcn2 = convertednum2.substring(convertednum2.indexOf("/") +1, convertednum2.length());
			int numeratorcn2int = Integer.parseInt(numeratorcn2); 
			int denomenatorcn2int = Integer.parseInt(denomenatorcn2); 

			int finalnumerator = 1;

			if (operation.equals("+")||operation.equals("-"))
			{

				int denomenatorcn1intA = denomenatorcn1int;
				numeratorcn1int *= denomenatorcn2int;
				denomenatorcn1int *= denomenatorcn2int;
				numeratorcn2int *= denomenatorcn1intA;

				if (operation.equals("+"))
				{
					finalnumerator = numeratorcn1int + numeratorcn2int;

				}
				else 
				{
					finalnumerator = numeratorcn1int - numeratorcn2int;
				}

			}	else
			{
				if (operation.equals("*"))
				{
					finalnumerator = numeratorcn1int*numeratorcn2int;
					denomenatorcn1int *= denomenatorcn2int;
				} else
				{
					finalnumerator = numeratorcn1int*denomenatorcn2int;
					denomenatorcn1int *= numeratorcn2int;
				}
			}
			if (denomenatorcn1int == 0)
			{
				Answer = "ERROR: Cannot divide by zero.";
			} else
			{
				int frac = 1;
				int factor = gcd(Math.max(finalnumerator, denomenatorcn1int), Math.min(finalnumerator, denomenatorcn1int));

				finalnumerator /= factor;
				denomenatorcn1int /= factor;

				if (denomenatorcn1int == 1 || denomenatorcn1int == -1)
				{
					frac = finalnumerator/denomenatorcn1int;
					Answer = frac + "";
				} else
				{
					if(Math.abs(finalnumerator) > Math.abs(denomenatorcn1int))
					{
						int wholenumfinal = finalnumerator/denomenatorcn1int;
						frac =  Math.abs(finalnumerator % denomenatorcn1int);
						Answer = wholenumfinal + "_" + frac + "/" + Math.abs(denomenatorcn1int);
					} else
					{
						if (finalnumerator == denomenatorcn1int)
						{
							Answer = "1";
						} else
						{
							if (finalnumerator == 0)
							{
								Answer = "0";
							}

							else
							{
								if (denomenatorcn1int < 0)
								{
									denomenatorcn1int *= -1;
									finalnumerator *= -1;
								}
								Answer = finalnumerator + "/" + denomenatorcn1int;
							}
						}
					}
				}

			}

			if (again=="yes")
			{
				Input = Answer + Input.substring(Input.indexOf(" ", space + 3), Input.length());
			}
		}
		return Answer;
	} 

	public static String valid(String num)
	{
		int i=0;
		String x = num.charAt(i) + "";
		if (!(x.equals("-")||x.equals("0")||x.equals("1")||x.equals("2")||x.equals("3")||x.equals("4")||x.equals("5")||x.equals("6")||x.equals("7")||x.equals("8")||x.equals("9")))
		{
			return "no";
		}
		i = 1;
		while (i<num.length())
		{
			x = num.charAt(i) + "";
			if (!(x.equals("0")||x.equals("1")||x.equals("2")||x.equals("3")||x.equals("4")||x.equals("5")||x.equals("6")||x.equals("7")||x.equals("8")||x.equals("9")))
			{
				return "no";
			}
			i++;
		} 
		return "yes";
	}

	public static String Converter(String number) 
	{
		int underscore = number.indexOf("_"); 
		int slash = number.indexOf("/");
		String fraction = number;
		String wholenumber = number;
		if (underscore != -1)
		{

			wholenumber = number.substring(0, underscore); 

			fraction = number.substring(underscore + 1, number.length()); 
		} else
		{
			if (slash != -1)
			{
				wholenumber = "0";
				fraction = number;
			} 	else
			{
				wholenumber = number;
				fraction = "0/1";
			}
		}

		String numerator = fraction.substring(0, fraction.indexOf("/"));

		String denomenator = fraction.substring(fraction.indexOf("/") +1, fraction.length());

		String vwhole = valid(wholenumber);
		String vnum = valid(numerator);
		String vden = valid(numerator);

		if (vwhole.equals("no")||vnum.equals("no")||vden.equals("no"))
		{
			return "no";
		} else
		{
			int numeratornum = Integer.parseInt(numerator); 
			int denomenatornum = Integer.parseInt(denomenator); 
			int wholenumbernum = Integer.parseInt(wholenumber); 
			int newnumerator = numeratornum;

			if (wholenumbernum<0)
			{
				newnumerator = denomenatornum*wholenumbernum - numeratornum; //part of fraction
			}
			else
			{
				newnumerator = denomenatornum*wholenumbernum + numeratornum; //part of fraction
			}
			int newdenomenator = denomenatornum;

			String newnum = newnumerator + "/" + newdenomenator;
			return newnum;
		}
	} 

	public static int gcd(int a, int b)
	{
		while (b != 0)
		{
			int c = a;
			a = b;
			b = c % b;
		}
		return Math.abs(a);
	}
}
