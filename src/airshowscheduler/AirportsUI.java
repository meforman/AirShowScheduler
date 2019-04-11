package airshowscheduler;

import java.util.List;

/** AirportsUI class to get, list, and delete airports for use in scheduling airshows.
 * @author Michael Forman, Richard Saavedra
 *
 */
public class AirportsUI {

	/**
	 * Each of the UI classes have a 'manageXxxx' method, which runs the menu etc., for that class.
	 */
	public static void manageAirports() {
		String menuText = "Enter your choice: "
				+ "\n 1. Add an Airport to the list " 
				+ "\n 2. View the list of Airports " 
				+ "\n 3. Edit Airport Info"
				+ "\n 4. View and Delete an Airport from the list " 
				+ "\n 5. Return to Main Menu.";
		int userPrompt = 0;

		do { userPrompt = AirShowSchedulerMain.displayMenu(menuText, 5);

			if (userPrompt == 1) {
				String airportName = getAirportName();
				String airportCode = getAirportCode();
				String airportFuel = getAirportFuel();
				int airportRunwayLen = getAirportRunway();
				Airports theAirports = new Airports(airportName, airportRunwayLen, airportFuel,
						airportCode.toUpperCase());
				AirShowBackEnd.saveAirports(theAirports);
			} 

			if (userPrompt == 2) {
				List<Airports> airportList = AirShowBackEnd.listAirports();
				displayAirports(airportList);
			} 
			
			if (userPrompt == 3) {
				List<Airports> airportList = AirShowBackEnd.listAirports();
				displayAirports(airportList);
				editAirport(airportList);
			} 

			if (userPrompt == 4) {
				List<Airports> airportList = AirShowBackEnd.listAirports();
				displayAirports(airportList);
				deleteAirport(airportList);
			} 
		} 
		while (userPrompt != 5); // End menu processing
		System.out.println("Returning to Main Menu...");
		return;
	} //manageAirports

	/** method to display the list of airports in formatted text
	 * @param theList of airports retrieved from the database
	 */
	public static void displaySingleAirports(Airports[] airportArray, int x) {
			System.out.println("\nAirport Information. \n");
			System.out.printf("%-7s%-22s%-34s%-15s%s", "Num.", "Airport Code", "Airport Name", "Fuel Avail",
					"Runway Length\n");
			System.out.printf("%-7s%-16s%-40s%-15s%s", "----", "------------", "------------------------", "----------",
					"--------------\n");
				System.out.printf("%-11s%-12s%-44s%-16s%s", " ", airportArray[x].getAirportCode(),
						airportArray[x].getAirportName(), airportArray[x].getAirportFuel(), airportArray[x].getAirportRunwayLen() + "\n");
			}
		
	
	/** method to display the list of airports in formatted text
	 * @param theList of airports retrieved from the database
	 */
	public static void displayAirports(List<Airports> theList) {
		if (theList.size() == 0)
			System.out.println("The Airport list is empty!");
		else {
			int airportCount = 1;
			System.out.println("\nHere is a list of all Airports. \n");
			System.out.printf("%-7s%-22s%-34s%-15s%s", "Num.", "Airport Code", "Airport Name", "Fuel Avail",
					"Runway Length\n");
			System.out.printf("%-7s%-16s%-40s%-15s%s", "----", "------------", "------------------------", "----------",
					"--------------\n");
			for (Airports toList : theList) {
				System.out.printf("%-11s%-12s%-44s%-16s%s", airportCount + ": ", toList.getAirportCode(),
						toList.getAirportName(), toList.getAirportFuel(), toList.getAirportRunwayLen() + "\n");
				airportCount++;
			}
		}
	}
	
	/** method to get airport name from the user.
	 * @return airportName name as a string
	 */
	public static String getAirportName() {
		int airportT = 0;
		String airportName = null;
		do {
			airportName = AirShowSchedulerMain.getStrings("Enter the Airport Name. (Limit 45 Chars.) ", "Name: ");
			if (airportName.length() < 1 || airportName.length() > 45) {
				System.out.println("You must enter a name. It may not be blank , nor exceed 45 chars.");
				airportT = 0;
			} // end if
			else
				return airportName;
		} // end do
		while (airportT == 0);
		return airportName;
	}
	
	/**method to get the airport code from the user
	 * @return airportCode String the three digit airport code 
	 */
	public static String getAirportCode() {
		int airportT = 0;
		String airportCode = null;
		do {
			airportCode = AirShowSchedulerMain.getStrings("\nEnter the three digit Airport Code. ", "Code: ");
			if (airportCode.length() < 3 || airportCode.length() > 3) {
				System.out.println("The airport code must be 3 characters.");
				airportT = 0;
			} // end if
			else
				return airportCode;
		} // end do
		while (airportT == 0);
		return airportCode;
	}
	
	/**method to get the airport fuel availability from the user
	 * @return airportFuel String of fuel type available at the airport
	 */
	public static String getAirportFuel() {
		int airportT = 0;
		int airportFuelNum = 1;
		do {
			airportFuelNum = AirShowSchedulerMain.getInt("\nEnter the fuel type 1) AvGas, 2) JetFuel, 3) Both. ", "Fuel Type: ");
			if (airportFuelNum < 1 || airportFuelNum > 3) {
				System.out.println("The fuel selection must be 1-3.");
				airportT = 0;
			} // end if
			else
				airportT = 1;
		} // end do
		while (airportT == 0);
		String airportFuel = "Both";
		if (airportFuelNum == 1)
			airportFuel = "AvGas";
		if (airportFuelNum == 2)
			airportFuel = "JetFuel";
		return airportFuel;
	}
	
	/**method to get the airport runway length from the user
	 * @return airportRunwayLen int length of the longest runway
	 */
	public static int getAirportRunway() {
		int airportT = 0;
		int airportRunwayLen;
		do {
			airportRunwayLen = AirShowSchedulerMain.getInt("\nEnter the Runway Length. ", "Runway Length: ");
			if (airportRunwayLen < 5000) {
				System.out.println("Minimum runway length is 5000 feet.");
				airportT = 0;
			} // end if
			else
				airportT = 1;
		} // end do
		while (airportT == 0);
		return airportRunwayLen;
	}
	
	/**method to list airports for the user to choose which to edit, select which field to edit, get new
	 * information, and send to the back end for updating.
	 * @param airportList list of airports to display for the user to choose which one to delete
	 */
	public static void editAirport(List<Airports> airportList) {
		String updateTable = "Airports";
		String updateRecord = "AirPortID";
		int badChoice = 0;
		int userChoice = 0;
		
		if (airportList.size() < 1) {
			System.out.println("There are no airports to edit.");
			return ;
		}
		do {
			userChoice = AirShowSchedulerMain.getInt("Choose an Airport to Edit: ", "");
			if (userChoice < 1 || userChoice > airportList.size()) {
			System.out.println("Please choose a number between 1 and " + airportList.size() + ".");
			badChoice = 1;
				} // End if
			else
				badChoice = 0;
		} 
		while (badChoice == 1);
		Airports[] airportArray = airportList.toArray(new Airports[airportList.size()]);
		int airportToEdit = userChoice - 1;
		displaySingleAirports(airportArray, airportToEdit);
		String menuText = "Select a field to edit: \n 1. Airport Code \n 2. Airport Name \n 3. Airport Fuel Availability"
				+ "\n 4. Airport Runway Length \n 5. Exit Editing Airport";
		userChoice = AirShowSchedulerMain.displayMenu(menuText, 5);
		
		Airports updatedAirport = new Airports(airportArray[airportToEdit].getAirportID(), airportArray[airportToEdit].getAirportName(), 
				airportArray[airportToEdit].getAirportRunwayLen(), airportArray[airportToEdit].getAirportFuel(), airportArray[airportToEdit].getAirportCode());
		String updateFieldString = "null";
		int updateRecordID = updatedAirport.getAirportID();
		if (userChoice == 2) 
			updateFieldString = "AirportName = '" + getAirportName();
		
		if (userChoice == 4) 
			updateFieldString = "AirportRunwayLength = '" + getAirportRunway();
		
		if (userChoice == 3) 
			updateFieldString = "AirportFuelAvailable = '" + getAirportFuel();
		
		if (userChoice == 1) {
			updateFieldString = "AirportCode = '" + getAirportCode();
		}
		AirShowBackEnd.updateRecord(updateTable, updateFieldString, updateRecord, updateRecordID);
		return;
	}
	
	/**method to list airports for the user to choose which to delete
	 * @param airportList list of airports to display for the user to choose which one to delete
	 */
	public static void deleteAirport(List<Airports> airportList) {
		if (airportList.size() < 1) {
			return;
		}
		int badChoice = 0;
		int userChoice = 0;
		do {
			userChoice = AirShowSchedulerMain.getInt("Choose an Airport to Delete: ", "");
			if (userChoice < 1 || userChoice > airportList.size()) {
			System.out.println("Please choose a number between 1 and " + airportList.size() + ".");
			badChoice = 1;
				} // End if
			else
				badChoice = 0;
		} 
		while (badChoice == 1);
		Airports[] airportArray = airportList.toArray(new Airports[airportList.size()]);
		AirShowBackEnd.deleteAirport(airportArray, userChoice);
	}
}
