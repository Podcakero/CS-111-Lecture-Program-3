public class LinkedList<T> implements ListInterface<T>
{
	/**
	 * The base class which a LinkedList is composed of. It consists of an element and a next which points to the next Node in the list
	 * @param <T>
	 */
	static class Node<T>
	{
		private Node<T> next = null;
		private T element;

		public Node(T element)
		{
			this.element = element;
		}

		public Node<T> getNext()
		{
			return next;
		}

		public void setNext(Node<T> next)
		{
			this.next = next;
		}

		public T getValue()
		{
			return element;
		}
	}


	Node<T> head;
	Node<T> tail;
	int size;

	/**
	 * Creates a new LinkedList.
	 */
	public LinkedList()
	{
		this.head = null;
		this.tail = null;
		size = 0;
	}

	@Override
	public void add(T element)
	{
		Node<T> node = new Node<>(element);

		//If we don't have a head, set a head. Else add the Node to the tail of the current tail
		if (head == null)
			head = node;
		else
			tail.setNext(node);

		//This new Node will ALWAYS become the tail
		tail = node;
		size++;
	}

	@Override
	public boolean removeAll(ListInterface<? extends T> list)
	{
		Node<T> parser = head;
		int index = 0;

		while (head != null)
		{
			if (list.find(parser.element) != -1)
				remove(index);
			index++;
		}

		return true;
	}

	@Override
	public T remove(int position)
	{
		Node<T> parser = head;

		for (int i = 0; i < position - 1; i++)
			parser = parser.next;

		Node<T> toRemove = parser.next;
		parser.setNext(toRemove.next);

		return toRemove.getValue();
	}

	@Override
	public void clear()
	{
		head = null;
	}

	public Node<T> getHead()
	{
		return head;
	}

	public Node<T> getTail()
	{
		return tail;
	}

	@Override
	public boolean isEmpty()
	{
		return head == null;
	}

	@Override
	public boolean isFull()
	{
		return false;
	}

	@Override
	public int getMaxSize()
	{
		return Integer.MAX_VALUE;
	}

	public int size()
	{
		return size;
	}

	@Override
	public int find(Object value)
	{
		Node<T> parser = head;

		for (int i = 0; i < size; i++)
		{
			if (parser.getValue() == value)
				return i;
			parser = parser.next;
		}

		return -1;
	}

	@Override
	public T get(int position)
	{
		Node<T> parser = head;

		for (int i = 0; i < position; i++)
			parser = parser.next;
		return parser.getValue();
	}

	@Override
	public void add(int position, T value) throws Exception
	{
		Node<T> parser = head;

		if (position < 0 || position > size)
			throw new Exception("Index is out of Bounds");

		for (int i = 0; i < position - 1; i++)
			parser = parser.next;

		Node<T> newNode = new Node<>(value);
		newNode.setNext(parser.next);
		parser.setNext(newNode);
	}

	/**
	 * Rund through the list and prints out each Employee including headers for each section as well as the Grand Total of each Employee Type
	 * @param list The list to print
	 */
	public static void print(LinkedList<Employee> list)
	{
		LinkedList.Node<Employee> parser = list.head;

		printHeader(parser.getValue().getType());

		for (int i = 0; i < list.size(); i++)
		{
			parser.getValue().calculatePay();
			parser.getValue().print();

			//This is the worst piece of code i have written
			if (parser.getNext() != null && !parser.getValue().getType().equals(parser.getNext().getValue().getType()))
			{
				printGrandTotalPay(parser.getValue().getType());
				printHeader(parser.getNext().getValue().getType());
			}

			//This is the second worst piece of code i have written
			switch (parser.getValue().getType().substring(0, 1))
			{
				case "C":
					Commissioned.addToGrandTotalPay(parser.getValue().getTotalPay());
					break;
				case "S":
					Salaried.addToGrandTotalPay(parser.getValue().getTotalPay());
					break;
				case "H":
					Hourly.addToGrandTotalPay(parser.getValue().getTotalPay());
			}

			parser = parser.next;
		}

		printGrandTotalPay(list.tail.getValue().getType());
	}

	/**
	 * Prints the grand total pay of the Employee Type
	 * @param employeeType The Employee Type who's grandTotal should be printed
	 */
	private static void printGrandTotalPay(String employeeType)
	{
		switch (employeeType.substring(0, 1))
		{
			case "C":
				System.out.println("Grand Total Pay: $" + Commissioned.getGrandTotalPay());
				break;
			case "S":
				System.out.println("Grand Total Pay: $" + Salaried.getGrandTotalPay());
				break;
			case "H":
				System.out.println("Grand Total Pay: $" + Hourly.getGrandTotalPay());
		}
	}

	/**
	 * Prints the header for the employee paystub
	 * @param employeeType The Employee Type for which to print the header
	 */
	private static void printHeader(String employeeType)
	{
		switch (employeeType.substring(0, 1))
		{
			case "C":
				System.out.println();
				System.out.printf("%-15s %-15s %-15s %-15s %-15s %-20s %-15s %-15s %-15s", "Type", "First name", "Last Name", "Title", "Employee ID", "Commission Rate", "Current Sales", "Salary", "Total Pay");
				System.out.println();
				break;
			case "S":
				System.out.println();
				System.out.printf("%-15s %-15s %-15s %-15s %-15s %-15s", "Type", "First name", "Last name", "Title", "EmployeeID", "Salary");
				System.out.println();
				break;
			case "H":
				System.out.println();
				System.out.printf("%-15s %-15s %-15s %-15s %-15s %-15s %-15s %-25s %-15s", "Type", "First name", "Last name", "Title", "Employee ID", "Hours Worked", "Hourly Rate", "Is Overtime Eligible", "Total Pay");
				System.out.println();
		}
	}

	/**
	 * Compared the first and next node of the linkedList and sorts them
	 * @param first The first Node
	 * @param next The next node
	 * @return The first Node
	 */
	private Node<T> sortedMerge(Node<T> first, Node<T> next)
	{
		Node<T> result;

		// Base cases
		if (first == null)
			return next;
		if (next == null)
			return first;

		// Pick either a or b, and recurse
		if (((Employee) first.element).compareTo((Employee)next.element) <= 0)
		{
			result = first;
			result.next = sortedMerge(first.next, next);
		}
		else
		{
			result = next;
			result.next = sortedMerge(first, next.next);
		}

		return result;
	}

	/**
	 * Sorts the given list using MergeSort
	 * @param head the head of the list to sort
	 * @return The head of the list now sorted
	 */
	public Node<T> mergeSort(Node<T> head)
	{
		// Base case : if head is null
		if (head == null || head.next == null)
			return head;

		// get the middle of the list
		Node<T> middle = getMiddle(head);
		Node<T> middleNext = middle.next;

		// set the next of middle Node to null
		middle.next = null;

		// Apply mergeSort on left list
		Node<T> left = mergeSort(head);

		// Apply mergeSort on right list
		Node<T> right = mergeSort(middleNext);

		// Merge the left and right lists
		return sortedMerge(left, right);
	}

	/**
	 * Utility function to get the middle of the linked list
 	 */
	private Node<T> getMiddle(Node<T> head)
	{
		if (head == null)
			return null;

		Node<T> slowParser = head;
		Node<T> fastParser = head;

		//Have fastParser goes twice as fast as slowParser. When fastParser reaches the end, then slowParser is at the middle
		while (fastParser.next != null && fastParser.next.next != null)
		{
			slowParser = slowParser.next;
			fastParser = fastParser.next.next;
		}
		return slowParser;
	}

}
