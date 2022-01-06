public class Salaried extends Employee
{
	private float salary;
	private static float grandTotalPay;

	public Salaried(int ID, String fName, String lName, String title, String type, float salary)
	{
		super(ID, fName, lName, title, type);

		this.salary = salary;
	}

	public float getSalary()
	{
		return salary;
	}

	public void setSalary(float salary)
	{
		this.salary = salary;
	}

	@Override
	public void calculatePay()
	{
		this.setTotalPay(salary / 24);
	}

	@Override
	public void print()
	{
		System.out.printf("%-15s %-15s %-15s %-15s %-15s %-15s", "Salaried", getFirstName(), getLastName(), getTitle(), getID(), getSalary());
		System.out.println();
	}

	public static void addToGrandTotalPay(float pay)
	{
		grandTotalPay += pay;
	}

	public static float getGrandTotalPay()
	{
		return grandTotalPay;
	}
}
