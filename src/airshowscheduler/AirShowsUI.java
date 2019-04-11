package airshowscheduler;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;


/** AirShowUI class to get, list, and delete air shows for use in scheduling airshows.
 * @author  Michael Forman, Richard Saavedra
 *
 */
public class AirShowsUI {
	
	/**
	 * Each of the UI classes have a 'manageXxxx' method, which runs the menu etc., for that class.
	 */
	public static void manageShows() {
		String menuText ="Enter your choice: "
				+ "\n 1. Add an Air Show to the list " 
				+ "\n 2. View the list of Shows "
				+ "\n 3. View list of Shows with Performances  "
				+ "\n 4. Edit an Air Show  "
				+ "\n 5. View and Delete a Show from the list  "
				+ "\n 6. Back to Main Menu.";
		int userPrompt = 0;
		List<Airports> theList = AirShowBackEnd.listAirports();
		List<AirShowActs> actList = AirShowBackEnd.listActs();
		
		do { userPrompt = AirShowSchedulerMain.displayMenu(menuText, 6);

			if (userPrompt == 1) {
				
				System.out.println("Select an Airport from the list. ");
				AirportsUI.displayAirports(theList);
				int showApId = getShowApId(theList);
				System.out.println(findAirportName(theList, showApId));
				String showName = getShowName();
				LocalDate showDate = getShowDate();
				
				AirShows theShows = new AirShows(showName, showApId, showDate);
				AirShowBackEnd.saveShows(theShows);
			} 

			if (userPrompt == 2) {
				List<AirShows> showList = AirShowBackEnd.listShows();
				displayShows(showList, theList);
			} 

			if (userPrompt == 3) {
				List<AirShows> showList = AirShowBackEnd.listShows();
				List<Performances> perfList = AirShowBackEnd.listPerfs();
				displayShowsWithActs(showList, theList, perfList, actList);
				
			} 
			
			if (userPrompt == 4) {
				List<AirShows> showList = AirShowBackEnd.listShows();
				displayShows(showList, theList);
				editAirShows(showList, theList);
			}
			
			if (userPrompt == 5) {
				List<AirShows> showList = AirShowBackEnd.listShows();
				displayShows(showList, theList);
				deleteShow(showList);
			} 
		} 
		while (userPrompt != 6); // End menu processing
		System.out.println("Returning to Main Menu...");
		return;
	}	
	
	/** Method to retrieve air show name from its code for display in a list
	 * @return showName String with with name of the show
	 */
	public static String getShowName() {
		int actT = 0;
		String showName = null;
		do {
			showName = AirShowSchedulerMain.getStrings("Enter the Air Show Name. (Limit 45 Chars.) ", "Name: ");
			if (showName.length() < 1 || showName.length() > 45) {
				System.out.println("You must enter a name. It may not be blank , nor exceed 45 chars.");
				actT = 0;
			} // end if
			else
				return showName;
		} // end do
		while (actT == 0);
		return showName;
	}
	
	/** Method takes list of airshows and formats output on console
	 * @param theList List of Airshows in the database
	 * @param airportList List of airports in the database 
	 */
	public static void displayShows(List<AirShows> theList, List<Airports> airportList) {
		DateTimeFormatter fd =  DateTimeFormatter.ofPattern("MM-dd-yyyy");
		if (theList.size() == 0)
			System.out.println("The Air Show Acts list is empty!");
		else {
			int showCount = 1;
			System.out.println("\nHere is a list of all Air Shows. ");
			System.out.printf("%-14s%-43s%-40s%s", "Num.", "Show Name", "Show Airport", "Show Date.\n");
			System.out.printf("%-7s%-43s%-45s%s", "----", "--------------------------------", "--------------------------------", "--------------\n");
			for (AirShows toList : theList) {
				String apName = findAirportName(airportList, toList.getShowAirport());
				System.out.printf("%-7s%-43s%-47s%s", showCount + ": ", toList.getShowName(), apName, fd.format(toList.getShowDate()) + "\n");
				showCount++;
			}
		}
	}
	
	public static void displaySingleShow(AirShows[] showArray, List<Airports> airportList, int x) {
		System.out.println("\nHere is a list of all Air Shows. ");
		System.out.printf("%-14s%-43s%-40s%s", "Num.", "Show Name", "Show Airport", "Show Date.\n");
		System.out.printf("%-7s%-43s%-45s%s", "----", "--------------------------------", "--------------------------------", "--------------\n");
		
			System.out.printf("%-7s%-43s%-47s%s", " ", showArray[x].getShowName(),
					findAirportName(airportList, showArray[x].getShowAirport()), showArray[x].getShowDate() + "\n");
		}
	
	/** Method that lists Airshows on the schedule, and any acts with each of them
	 * @param theList List with list of airshows in the database
	 * @param airportList List with name of airports for display
	 * @param perfList List with list of performances scheduled for any show
	 * @param actList List with name of acts for display
	 */
	public static void displayShowsWithActs(List<AirShows> theList, List<Airports> airportList, List<Performances> perfList, List<AirShowActs> actList) {
		DateTimeFormatter fd =  DateTimeFormatter.ofPattern("MM-dd-yyyy");
		if (theList.size() == 0)
			System.out.println("The Air Show Acts list is empty!");
		else {
			int showCount = 1;
			System.out.println("\nHere is a list of all Air Shows. ");
			System.out.printf("%-14s%-43s%-40s%s", "Num.", "Show Name", "Show Airport", "Show Date.\n");
			System.out.printf("%-7s%-43s%-45s%s", "----", "--------------------------------", "--------------------------------", "--------------\n");
			for (AirShows toList : theList) {
				String apName = findAirportName(airportList, toList.getShowAirport());
				System.out.printf("%-7s%-43s%-47s%s", showCount + ": ", toList.getShowName(), apName, fd.format(toList.getShowDate()) + "\n");
				for (Performances checkPerfList : perfList){
					if (checkPerfList.getPerfAirShowId() == toList.getShowID()) {
				PerformancesUI.displayPerfsByShow(perfList, theList, actList, toList.getShowID());
				break;
				}
				}
				showCount++;
			}
		}
	}
	
	public static void editAirShows(List<AirShows> showList, List<Airports> theList) {
		String updateTable = "AirShows";
		String updateRecord = "AirShowID";
		int badChoice = 0;
		int userChoice = 0;
		
		if (showList.size() < 1) {
			System.out.println("There are no Air Shows to edit.");
			return ;
		}
		do {
			userChoice = AirShowSchedulerMain.getInt("Choose an Air Show to Edit: ", "");
			if (userChoice < 1 || userChoice > showList.size()) {
			System.out.println("Please choose a number between 1 and " + showList.size() + ".");
			badChoice = 1;
				} // End if
			else
				badChoice = 0;
		} 
		while (badChoice == 1);
		AirShows[] showArray = showList.toArray(new AirShows[showList.size()]);
		int showToEdit = userChoice - 1;
		displaySingleShow(showArray, theList, showToEdit);
		String menuText = "Select a field to edit: \n 1. Air Show Name \n 2. Show Airport  \n 3. Air Show Date";
		userChoice = AirShowSchedulerMain.displayMenu(menuText, 3);
		
		AirShows updatedShow = new AirShows(showArray[showToEdit].getShowID(), showArray[showToEdit].getShowName(), 
				showArray[showToEdit].getShowAirport(), showArray[showToEdit].getShowDate());
		String updateFieldString = "null";
		int updateRecordID = updatedShow.getShowID();
		if (userChoice == 1) 
			updateFieldString = "AirshowName = '" + getShowName();
		
		if (userChoice == 2) 
			updateFieldString = "AirShowAirportID = '" + getShowApId(theList);
		
		if (userChoice == 3) 
			updateFieldString = "AirShowDate = '" + getShowDate();
		
		AirShowBackEnd.updateRecord(updateTable, updateFieldString, updateRecord, updateRecordID);
		return;
	}
	
	/** Method to retrieve the name of the airport from its ID
	 * @param theList List of airports in the database
	 * @param apRecordID integer with AirportID of which name is needed.
	 * @return String with name of airport
	 */
	public static String findAirportName(List<Airports> theList, int apRecordID) {
		String airportName = null;
		for (Airports eachAirport : theList) {
			if (eachAirport.getAirportID() == apRecordID) {
				return eachAirport.getAirportName();	
				}
		}
		return airportName;
	}
	
	/**Method to retrieve the airport ID number for the airport for an airshow. Takes integer prompt from list displayed.
	 * @param theList list of Airports in the database
	 * @return int with ID of airport for the airshow
	 */
	public static int getShowApId(List<Airports> theList) {
		if (theList.size() < 1) {
			System.out.println("There are no airports to add!");
			return 0;
		}
		int badChoice = 0;
		int userChoice = 0;
		do {
			userChoice = AirShowSchedulerMain.getInt("Choose an Airport for the Show: ", "");
			if (userChoice < 1 || userChoice > theList.size()) {
			System.out.println("Please choose a number between 1 and " + theList.size() + ".");
			badChoice = 1;
				} // End if
			else
				badChoice = 0;
		} 
		while (badChoice == 1);
		Airports[] showArray = theList.toArray(new Airports[theList.size()]);
		return showArray[userChoice - 1].getAirportID();
	}
	
	/** Method to delete and airshow from a previously displayed list. Asks for the number on the list, converts it to the 
	 *  AirShowID in the database and deletes it.
	 * @param showList List of airports int he database
	 */
	public static void deleteShow(List<AirShows> showList) {
		if (showList.size() < 1) {
			return;
		}
		int badChoice = 0;
		int userChoice = 0;
		do {
			userChoice = AirShowSchedulerMain.getInt("Choose an Air Show to Delete (0 to abort): ", "");
			if (userChoice < 1 || userChoice > showList.size()) {
				if (userChoice == 0) {
					System.out.println("Aborting, returning to Air Show Menu.");
					return;
				}
			System.out.println("Please choose a number between 1 and " + showList.size() + ".");
			badChoice = 1;
				} // End if
			else
				badChoice = 0;
		} 
		while (badChoice == 1);
		AirShows[] showArray = showList.toArray(new AirShows[showList.size()]);
		AirShowBackEnd.deleteShows(showArray, userChoice);
	}
		
	/**
	 * Method to take date input from use and ensure proper format and appropriate
	 * for airshow entry
	 * @return dueDate
	 */
	public static LocalDate getShowDate() {
		LocalDate getDate = null;
		int good = 0;
		do {
			try {
				String getTheDate = AirShowSchedulerMain.getStrings("Enter the Air Show date. ", "Show date: ");
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("M/d/yyyy");
				getDate = LocalDate.parse(getTheDate, formatter);
				if (getDate.isBefore(LocalDate.now())) {
					System.out.println("That show date is in the past, please re-enter. ");
					good = 0;
				} else {
					good = 1;
				}
			} // End Try
			catch (java.time.format.DateTimeParseException excep) {
				System.out.println("Bad Date format please re-enter like 3/14/2017");
			} // End Catch

		} // End Do
		while (good == 0);
		return getDate;
	}// End getMyDate
	
}
