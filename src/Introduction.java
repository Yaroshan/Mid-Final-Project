import java.util.Scanner;

public class Introduction
	{
	static String name;

	public static void main(String[] args)
		{
		Introduction1();
		Rooms.createMap();
		}

	static void Introduction1()
		{
		Scanner input = new Scanner(System.in);
		System.out.println("Welcome Stranger, Please Enter Your Name");
		name = input.nextLine();
		System.out
				.println("Welcome to the world of Hyrule, you have just woken up from a deep sleep."
						+ " " + name + ". Please enter anything to continue.");
		String input1 = Input.getInput();
		


		}
	}
