package airshowscheduler;

import java.util.InputMismatchException;
import java.util.Scanner;

/** Main class to perform menu and common functions 
 * @author Michael Forman, Richard Saavedra
 *
 */
public class AirShowSchedulerMain {

	public static void main(String[] args) {
		String menuString = "Enter your choice: " 
				+ "\n 1. Manage Airports. " 
				+ "\n 2. Manage Air Show Acts. "
				+ "\n 3. Manage Air Shows.  " 
				+ "\n 4. Manage Performances.  "
				+ "\n 5. Exit";
		int menuNumber = 5;
		
		int userPrompt = 0;
		do { userPrompt = displayMenu(menuString, menuNumber);
		
		if (userPrompt == 1) {
			AirportsUI.manageAirports();
		}
		
		if (userPrompt == 2) {
			AirShowActsUI.manageActs();
		}
		
		if (userPrompt == 3) {
			AirShowsUI.manageShows();
		}
		
		if (userPrompt == 4) {
			PerformancesUI.managePerfs();
		}
		
		} while (userPrompt != 5); // End menu processing
		System.out.println("Thanks for using Air Show Scheduler!");
		System.exit(0);
	}
	
	/**
	 * Method for creating and taking menu selection used in all classes.
	 * @param menuTest String containing menu text
	 * @param menuNumber int containing number of menu choices in the menu.
	 * @return menu choice
	 */
	public static int displayMenu(String menuText, int menuNumber) {// My home-grown menu display method
		boolean badMenu = true;
		int userChoice = 0;

		do {
			System.out.println(menuText);
			System.out.print("Choice: ");
				userChoice = getInt("","");
				if (userChoice < 1 || userChoice > menuNumber) {
					System.out.println("Please choose a number between 1 and " + menuNumber+ ".");
					System.out.print("\nChoice: ");
					badMenu = false;
				} // End if
				else
					badMenu = true;
				return userChoice;
		} // End do
		while (!badMenu);
	}// end displayMenu
	
	/** method accepts prompt strings and asks for integers confirms entry is integer.
	 * @param promptOne String containing text for integer prompts
	 * @param promptTwo String containing prompt for integer prompts
	 * @return integer
	 */
	public static int getInt(String promptOne, String promptTwo) {
		boolean badChoice = true;
		int returnInt = 0;
		do {
			try {
				System.out.println(promptOne);
				System.out.print(promptTwo);
				Scanner dinput = new Scanner(System.in);
				returnInt = dinput.nextInt();
				badChoice = true;
				return returnInt;
			} catch (InputMismatchException f) {
				System.out.println("Entry must be an integer. Re-enter. ");
				badChoice = false;
			} // end catch
		}
		while (!badChoice);
		return returnInt;
	}

	/** method accepts prompt strings and asks for Strings.
	 * @param promptOne String containing text for String prompts
	 * @param promptTwo String containing prompt for String prompts
	 * @return String
	 */
	public static String getStrings(String promptOne, String promptTwo) {
		System.out.println(promptOne);
		System.out.print(promptTwo);
		Scanner sinput = new Scanner(System.in);
		String returnString = sinput.nextLine();
		return returnString;
		
	}

}
