public class Commissioned extends Employee
{
	private float salary;
	private float commissionRate;
	private float commissionSales;
	private float threshold;
	private static float grandTotalPay;

	public Commissioned(int ID, String fName, String lName, String title, String type, float salary, float threshold, float commissionRate)
	{
		super(ID, fName, lName, title, type);

		this.salary = salary;
		this.threshold = threshold;
		this.commissionRate = commissionRate;
	}

	public float getSalary()
	{
		return salary;
	}

	public float getCommissionRate()
	{
		return commissionRate;
	}

	public float getCommissionSales()
	{
		return commissionSales;
	}

	public float getThreshold()
	{
		return threshold;
	}

	public void setCommissionSales(float sales)
	{
		commissionSales = sales;
	}

	@Override
	public void calculatePay()
	{
		float totalPay = (salary / 24);

		if (commissionSales > threshold)
			totalPay += ((commissionSales - threshold) * commissionRate);

		setTotalPay(totalPay);
	}

	@Override
	public void print()
	{
		System.out.printf("%-15s %-15s %-15s %-15s %-15s %-20s %-15s %-15s %-15s", "Commissioned", getFirstName(), getLastName(), getTitle(), getID(), getCommissionRate(), getCommissionSales(), getSalary(), getTotalPay());
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
