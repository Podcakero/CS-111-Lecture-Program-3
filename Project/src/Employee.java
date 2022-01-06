public abstract class Employee implements Comparable<Employee>
{
	private int ID;
	private String firstName;
	private String lastName;
	private String title;
	private float totalPay;
	private String type;

	public Employee(int ID, String fName, String lName, String title, String type)
	{
		this.ID = ID;
		firstName = fName;
		lastName = lName;
		this.title = title;
		this.type = type;
	}

	public int getID()
	{
		return ID;
	}

	public String getFirstName()
	{
		return firstName;
	}

	public String getLastName()
	{
		return lastName;
	}

	public String getTitle()
	{
		return title;
	}

	public String getType()
	{
		return type;
	}

	public float getTotalPay()
	{
		return totalPay;
	}

	public void setTotalPay(float totalPay)
	{
		this.totalPay = totalPay;
	}

	@Override
	public int compareTo(Employee employee2)
	{
		//Grab the integer representations of the employee types
		int employee1Type = convertTypeToInt(this);
		int employee2Type = convertTypeToInt(employee2);

		//If they are of the same type, compare the ID's
		if (employee1Type - employee2Type == 0)
			return this.getID() - employee2.getID();

		//Return the types subtracted
		return employee1Type - employee2Type;
	}

	private int convertTypeToInt(Employee employee)
	{
		switch (employee.getType())
		{
			case "Commissioned":
				return 3;
			case "Salaried":
				return 2;
			case "Hourly":
				return 1;
		}

		//This should never happen but just in case it'll be set to 0
		return 0;
	}

	public abstract void calculatePay();

	public abstract void print();
}
