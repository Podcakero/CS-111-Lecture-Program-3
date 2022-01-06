import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Main
{
	private static LinkedList<Employee> list;

	public static void main(String[] args)
	{
		list = new LinkedList<>();

		Scanner scan = new Scanner(System.in);

		System.out.println("File name: ");
		readFile(scan.nextLine());

		//Sort and print
		list.head = list.mergeSort(list.head);
		LinkedList.print(list);
	}

	/**
	 * Reads in the file with the given name
	 * @param fileName The name of the file to read
	 */
	private static void readFile(String fileName)
	{
		try
		{
			Path path = Paths.get(fileName);
			Scanner scan = new Scanner(path);

			while (scan.hasNextLine())
				readLine(scan);
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}

	//This is possible the ugliest method i've written in awhile. Sure it works but it's ugly and hard to follow
	/**
	 * Reads the line and creates the proper Employee
	 * @param scan The scanner object referring to the current line
	 */
	private static void readLine(Scanner scan)
	{
		//Read in the base values that every employee has
		String type = scan.next();
		int ID = scan.nextInt();
		String firstName = scan.next();
		String lastName = scan.next();
		String title = scan.next();
		float salary;

		//Each case has different values that we need to read-in, so we must differentiate between then.
		switch (type)
		{
			case "C":
				salary = scan.nextFloat();
				float commissionRate = scan.nextFloat();
				float threshold = scan.nextFloat();
				float commissionSales = scan.nextFloat();

				//Add to list
				list.add(new Commissioned(ID, firstName, lastName, title, "Commissioned", salary, threshold, commissionRate));

				//We just added our employee, thus Tail is guaranteed to be a commission employee
				((Commissioned) list.getTail().getValue()).setCommissionSales(commissionSales);
				break;
			case "S":
				salary = scan.nextFloat();

				//Add to list
				list.add(new Salaried(ID, firstName, lastName, title, "Salaried", salary));
				break;
			case "H":
				float hourlyRate = scan.nextFloat();
				boolean overtime;

				//Since Y/N is not a true boolean, we must convert it
				String oTime = scan.next();
				overtime = oTime.equals("Y");

				float hoursWorked = scan.nextFloat();

				//Add to list
				list.add(new Hourly(ID, firstName, lastName, title, "Hourly", hourlyRate, overtime));

				//We just added our employee, thus Tail is guaranteed to be an Hourly employee
				((Hourly) list.getTail().getValue()).setHoursWorked(hoursWorked);
		}
	}
}
