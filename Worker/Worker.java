package worker;

public interface Worker
{
	//Returns the number of hours
	// per week the worker works
	int getHours();

	// Returns the salary of the worker
	double getSalary();

	// Returns the number of vacation days
	// per year the worker can take
	int getVacationDays();

	//Returns the type of form the worker
	// must use to report a vacation
	String getVacationForm();
}
