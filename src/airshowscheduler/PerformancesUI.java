package airshowscheduler;

import java.time.LocalDate;
import java.util.List;


public class PerformancesUI {
	
	/**
	 * Each of the UI classes have a 'manageXxxx' method, which runs the menu etc., for that class.
	 */
	public static void managePerfs() {
		int userPrompt = 0;
		List<AirShows> showList = AirShowBackEnd.listShows();
		List<AirShowActs> actList = AirShowBackEnd.listActs();
		List<Airports> apList = AirShowBackEnd.listAirports();
		List<Performances> picPerfs = AirShowBackEnd.listPerfs();
		String menuText = "Enter your choice: "
				+ "\n Note: To change a performance, delete and re-enter."
				+ "\n 1. Add a Performance to the list " 
				+ "\n 2. View the list of Performances "
				+ "\n 3. View and Delete a Performance from the list  "
				+ "\n 4. Back to Main Menu.";
		
		do { userPrompt = AirShowSchedulerMain.displayMenu(menuText, 5);

			if (userPrompt == 1) {
				
				System.out.println("First, select an Air Show from the list. ");
				AirShowsUI.displayShows(showList, apList);
				int showId = getShowId(showList);
				String asName = findAirShowName(showList, showId);
				System.out.println(asName);
				int runwayLimit = getAirportRunway(apList, showList, showId);
				System.out.println("Runway length for " + asName + " is " + runwayLimit + " feet.");
				System.out.println("Next, select an Air Show Act from the list. ");
				List<AirShowActs> qualActs = AirShowBackEnd.listQualActs(showId);
				displayQualActs(picPerfs, qualActs, showId);
				int actId = getActId(qualActs, showList);
				
				Performances thePerfs = new Performances(showId, actId);
				AirShowBackEnd.savePerfs(thePerfs);
			} 

			if (userPrompt == 2) {
				
				List<Performances> perfList = AirShowBackEnd.listPerfs();
				displayPerfs(perfList, showList, actList);
			} 

			if (userPrompt == 3) {
				List<Performances> perfList = AirShowBackEnd.listPerfs();
				displayPerfs(perfList, showList, actList);
				deletePerf(perfList);
			} 
		} 
		while (userPrompt != 4); // End menu processing
		System.out.println("Returning to Main Menu...");
		return;
	}	
	
	/** Method to retrieve the runway length for a particular air show airport so appropriate acts can be scheduled
	 * @param apList List with airports from database
	 * @param showList List with airshows from database
	 * @param showId int with ID number of the show who's airport's runway must be retrieved
	 * @return int with runway length
	 */
	public static int getAirportRunway(List<Airports> apList, List<AirShows> showList, int showId ) {
		int apId = 0;
		int apRunway = 0;
		for (AirShows airshow : showList) {
			if (airshow.getShowID() == showId) {
				apId = airshow.getShowAirport();
			}
		for (Airports airport : apList) {
			if (airport.getAirportID() == apId) {
				apRunway = airport.getAirportRunwayLen();
				return apRunway;
				}
			}
		}
		return apRunway;
	}
	
	/** Method used after displaying list of air shows for user to select air show to schedule performance
	 * @param theList List of airshows from database
	 * @return int of AirShowID for air show chosen for a performance
	 */
	public static int getShowId(List<AirShows> theList) {
		if (theList.size() < 1) {
			System.out.println("There are no Air Shows to add!");
			return 0;
		}
		int badChoice = 0;
		int userChoice = 0;
		do {
			userChoice = AirShowSchedulerMain.getInt("Choose an Air Show for the Performance: ", "");
			if (userChoice < 1 || userChoice > theList.size()) {
			System.out.println("Please choose a number between 1 and " + theList.size() + ".");
			badChoice = 1;
				} // End if
			else
				badChoice = 0;
		} 
		while (badChoice == 1);
		AirShows[] showArray = theList.toArray(new AirShows[theList.size()]);
		return showArray[userChoice - 1].getShowID();
	}
	
	/** Method used after displaying list of air show acts for user to select air show act to schedule performance
	 * @param theList List of airshows from database
	 * @param showList List of airshows from database
	 * @return int of AirShowActID for air show chosen for a performance
	 */
	public static int getActId(List<AirShowActs> theList, List<AirShows> showList) {
			if (theList.size() < 1) {
			System.out.println("There are no Air Show Acts to add!");
			return 0;
		}
		int badChoice = 0;
		int userChoice = 0;
		do {
			userChoice = AirShowSchedulerMain.getInt("Choose an Air Show Act for the Performance: ", "");
			if (userChoice < 1 || userChoice > theList.size()) {
			System.out.println("Please choose a number between 1 and " + theList.size() + ".");
			badChoice = 1;
				} // End if
			else
				badChoice = 0;
		} 
		while (badChoice == 1);
		AirShowActs[] actArray = theList.toArray(new AirShowActs[theList.size()]);
		return actArray[userChoice - 1].getActID();
	}
	
	/** Method to retrieve name of an airshow using its ID
	 * @param theList List of airshows
	 * @param asRecordID or airshow to be retrieved
	 * @return airshowName String with name of Airshow
	 */
	public static String findAirShowName(List<AirShows> theList, int asRecordID) {
		String airshowName = null;
		for (AirShows eachAirShow : theList) {
			if (eachAirShow.getShowID() == asRecordID) {
				return eachAirShow.getShowName();	
				}
		}
		return airshowName;
	}
	
	/** Method to retrieve date of an airshow using its ID
	 * @param theList List of airshows
	 * @param asRecordID or airshow to be retrieved
	 * @return airshowDate LocalDate with date of Airshow
	 */
	public static LocalDate findAirShowDate(List<AirShows> theList, int asRecordID) {
		LocalDate airshowDate = null;
		for (AirShows eachAirShow : theList) {
			if (eachAirShow.getShowID() == asRecordID) {
				return eachAirShow.getShowDate();	
				}
		}
		return airshowDate;
	}
	
	/** Method to retrieve name of an air show act using its ID
	 * @param theList List of air shows acts
	 * @param asRecordID or air show acts to be retrieved
	 * @return airshowactName String with name of AirshowAct
	 */
	public static String findAirShowActName(List<AirShowActs> theList, int asRecordID) {
		String airshowactName = null;
		for (AirShowActs eachAirShow : theList) {
			if (eachAirShow.getActID() == asRecordID) {
				return eachAirShow.getActName();	
				}
		}
		return airshowactName;
	}
	
	/** Method to display performance in proper format on console
	 * @param perfList List of all performances from database
	 * @param showList List of all airshows from database
	 * @param actList List of all acts from database
	 */
	public static void displayPerfs(List<Performances> perfList, List<AirShows> showList, List<AirShowActs> actList) {
		if (perfList.size() == 0)
			System.out.println("The Air Show Acts list is empty!");
		else {
			int perfCount = 1;
			System.out.println("\nHere is a list of all scheduled Air Show Performances. ");
			System.out.printf("%-14s%-43s%s", "Num.", "Show Name", "Act Name\n");
			System.out.printf("%-7s%-43s%s", "----", "--------------------------------", "--------------------------------\n");
			for (Performances toList : perfList) {
				String asName = findAirShowName(showList, toList.getPerfAirShowId());
				String asaName = findAirShowActName(actList, toList.getPerfActId());
				System.out.printf("%-7s%-43s%s", perfCount + ": ", asName, asaName + "\n");
				perfCount++;
			}
		}
	}
	
	/** Method to display performance in proper format on console for a given airshow
	 * @param perfList List of all performances from database
	 * @param showList List of all airshows from database
	 * @param actList List of all acts from database
	 * @param showId int for air show to be displayed
	 */
	public static void displayPerfsByShow(List<Performances> perfList, List<AirShows> showList, List<AirShowActs> actList, int showId) {
		if (perfList.size() == 0)
			System.out.println("The Air Show Acts list is empty!");
		else {
			System.out.println("Acts Scheduled: ");
			for (Performances toList : perfList) {
				if (toList.getPerfAirShowId() == showId) {
				String asaName = findAirShowActName(actList, toList.getPerfActId());
				System.out.printf("%-20s", "          " + asaName + "\n");
				}
			}
			System.out.println("\n");
		}
	}
	
	/** Method uses list of acts derived from a special stored procedure in the MySQL server that
	 *  only returns a list of acts that qualify to fly from the airport associated with the show for
	 *  which the act is being scheduled. Variables used in the procedure compare runway length and fuel
	 *  at the airport with the air show act's requirements. This method also calls "checkDates" to make
	 *  sure the act hasn't been assigned another show on the date of the show currently scheduled
	 * @param picPerfs List with performances available for a given show. 
	 * @param theList List containing list of Air Show Acts
	 * @param showId ID of the airshow for scheduling the performance
	 */
	public static void displayQualActs(List<Performances> picPerfs, List<AirShowActs> theList, int showId) {
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
				if (!(checkDates(picPerfs, toList.getActID(), showId )))
				System.out.printf("%-7s%-47s%-15s%-16s%s", airshowCount + ": ", toList.getActName(),
						toList.getActCost(), toList.getActFuel(), toList.getActRunway() + "\n");
				airshowCount++;
			}
		}
	}

	/** Method to check if a performer is already scheduled for an air show on the same date
	 * @param picPerfs List special list from previous method with only qualified shows for airport
	 * @param actId int with actID who's date is to be checked
	 * @param showId int with date for the airshow to check if the act is already scheduled
	 * @return true if act is already scheduled, false if not
	 */
	public static boolean checkDates(List<Performances> picPerfs, int actId, int showId) {
		for (Performances checkList : picPerfs) {
			if (checkList.getPerfActId() ==  actId && checkList.getPerfAirShowId() == showId)
				return true;
		}
		return false;
	}
		
		/** Method to take menu selection from previously displayed list and delete it from the database
		 * @param perfList list of all performances
		 */
		public static void deletePerf(List<Performances> perfList) {
			if (perfList.size() < 1) {
				return;
			}
			int badChoice = 0;
			int userChoice = 0;
			do {
				userChoice = AirShowSchedulerMain.getInt("Choose an Air Show Performance to Delete (0 to abort): ", "");
				if (userChoice < 1 || userChoice > perfList.size()) {
					if (userChoice == 0) {
						System.out.println("Aborting, returning to Performances Menu.");
						return;
					}
				System.out.println("Please choose a number between 1 and " + perfList.size() + ".");
				badChoice = 1;
					} // End if
				else
					badChoice = 0;
			} 
			while (badChoice == 1);
			Performances[] perfArray = perfList.toArray(new Performances[perfList.size()]);
			AirShowBackEnd.deletePerfs(perfArray, userChoice);
		}
	
	
}