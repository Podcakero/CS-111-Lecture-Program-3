public class Hourly extends Employee
{
	private float hoursWorked;
	private float hourlyRate;
	private boolean overtimeEligible;
	private static float grandTotalPay;

	public Hourly(int ID, String fName, String lName, String title, String type, float hourlyRate, boolean overtimeEligible)
	{
		super(ID, fName, lName, title, type);

		this.hourlyRate = hourlyRate;
		this.overtimeEligible = overtimeEligible;
	}

	public float getHoursWorked()
	{
		return hoursWorked;
	}

	public float getHourlyRate()
	{
		return hourlyRate;
	}

	public boolean isOvertimeEligible()
	{
		return overtimeEligible;
	}

	public void setHoursWorked(float hoursWorked)
	{
		this.hoursWorked = hoursWorked;
	}

	@Override
	public void calculatePay()
	{
		float totalPay = hoursWorked * hourlyRate;

		if (overtimeEligible)
			totalPay += (hoursWorked - 80) * 1.5 * hourlyRate;

		setTotalPay(totalPay);
	}

	@Override
	public void print()
	{
		System.out.printf("%-15s %-15s %-15s %-15s %-15s %-15s %-15s %-25s %-15s", "Hourly", getFirstName(), getLastName(), getTitle(), getID(), getHoursWorked(), getHourlyRate(), isOvertimeEligible(), getTotalPay());
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
