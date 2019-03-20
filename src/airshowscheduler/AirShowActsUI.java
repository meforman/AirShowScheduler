package airshowscheduler;

import java.util.List;

/** AirShowActsUI class to get, list, and delete air show acts for use in scheduling airshows.
 * @author Michael Forman, Richard Saavedra
 *
 */
public class AirShowActsUI {

	/**
	 * Each of the UI classes have a 'manageXxxx' method, which runs the menu etc., for that class.
	 */
	public static void manageActs() {
		String menuText = "Enter your choice: "
				+ "\n 1. Add an Act to the list " 
				+ "\n 2. View the list of Acts "
				+ "\n 3. View and Delete an Act from the list  " 
				+ "\n 4. Back to Main Menu.";
		
		int userPrompt = 0;

		do { userPrompt = AirShowSchedulerMain.displayMenu(menuText, 4);

			if (userPrompt == 1) {
				String actName = getActName();
				int actCost = getActCost();
				String actFuel = getActFuel();
				int actRunway = AirportsUI.getAirportRunway();
				AirShowActs theActs = new AirShowActs(actName, actRunway, actFuel, actCost);
				AirShowBackEnd.saveActs(theActs);
			} 

			if (userPrompt == 2) {
				List<AirShowActs> actList = AirShowBackEnd.listActs();
				displayActs(actList);
			} 

			if (userPrompt == 3) {
				List<AirShowActs> actList = AirShowBackEnd.listActs();
				displayActs(actList);
				deleteAct(actList);
			} 
		} 
		while (userPrompt != 4); // End menu processing
		System.out.println("Returning to Main Menu...");
		return;
	}
		
	/** method to get act name from the user.
	 * @return actName name as a string
	 */
	public static String getActName() {
		int actT = 0;
		String actName = null;
		do {
			actName = AirShowSchedulerMain.getStrings("Enter the Act Name. (Limit 45 Chars.) ", "Name: ");
			if (actName.length() < 1 || actName.length() > 45) {
				System.out.println("You must enter a name. It may not be blank , nor exceed 45 chars.");
				actT = 0;
			} // end if
			else
				return actName;
		} // end do
		while (actT == 0);
		return actName;
	}
	
	/** method to get act fuel type from the user.
	 * @return actFuel name as a string
	 */
	public static String getActFuel() {
		int actT = 0;
		int actFuelNum = 1;
		do {
			actFuelNum = AirShowSchedulerMain.getInt("\nEnter the fuel type 1) AvGas, 2) JetFuel, 3) None Required. ", "Fuel Type: ");
			if (actFuelNum < 1 || actFuelNum > 3) {
				System.out.println("The fuel selection must be 1-3.");
				actT = 0;
			} // end if
			else
				actT = 1;
		} // end do
		while (actT == 0);
		String actFuel = "None";
		if (actFuelNum == 1)
			actFuel = "AvGas";
		if (actFuelNum == 2)
			actFuel = "JetFuel";
		return actFuel;
	}
	
	/** method to get act cost from the user.
	 * @return actCost amount as an int
	 */
	public static int getActCost() {
		int actT = 0;
		int actCost;
		do {
			actCost = AirShowSchedulerMain.getInt("\nEnter the Act Cost per Show. ", "Act Cost: ");
			if (actCost < 500) {
				System.out.println("Minimum cost is $500.");
				actT = 0;
			} // end if
			else
				actT = 1;
		} // end do
		while (actT == 0);
		return actCost;
	}
	
	/**method to list air show acts for the user 
	 * @param theList list of air show acts to display for the user 
	 */
	public static void displayActs(List<AirShowActs> theList) {
		if (theList.size() == 0)
			System.out.println("The Air Show Acts list is empty!");
		else {
			int airshowCount = 1;
			System.out.println("\nHere is a list of all Air Show Acts. \n");
			System.out.printf("%-14s%-38s%-14s%-16s%s", "Num.", "Act Name", "Act Cost", "Act Fuel Req.",
					"Runway Length\n");
			System.out.printf("%-7s%-42s%-16s%-16s%s", "----", "--------------------------------", "--------------", "--------------",
					"---------------\n");
			for (AirShowActs toList : theList) {
				System.out.printf("%-7s%-47s%-15s%-16s%s", airshowCount + ": ", toList.getActName(),
						toList.getActCost(), toList.getActFuel(), toList.getActRunway() + "\n");
				airshowCount++;
			}
		}
	}
	
	/**method to list air show acts for the user to choose which to delete
	 * @param theList list of air show acts to display for the user to choose which one to delete
	 */
	public static void deleteAct(List<AirShowActs> actList) {
		if (actList.size() < 1) {
			return;
		}
		int badChoice = 0;
		int userChoice = 0;
		do {
			userChoice = AirShowSchedulerMain.getInt("Choose an Air Show Act to Delete: ", "");
			if (userChoice < 1 || userChoice > actList.size()) {
			System.out.println("Please choose a number between 1 and " + actList.size() + ".");
			badChoice = 1;
				} // End if
			else
				badChoice = 0;
		} 
		while (badChoice == 1);
		AirShowActs[] actArray = actList.toArray(new AirShowActs[actList.size()]);
		AirShowBackEnd.deleteActs(actArray, userChoice);
	}
}
