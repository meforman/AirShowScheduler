package airshowscheduler;

import java.time.LocalDate;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @author Michael
 *
 */
@Entity
@Table(name = "airshows")

public class AirShows {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "AirShowID")
	private int showID;
	
	@Column(name = "AirshowName")
	private String showName;
	
	@Column(name = "AirShowAirportID")
	private int showAirport;
	
	@Column(name = "AirShowDate")
	private LocalDate showDate;
	
	public AirShows() {};
	
	public AirShows(String asName, int asApId, LocalDate asDate) {
		this.setShowName(asName);
		this.setShowAirport(asApId);
		this.setShowDate(asDate);
	}

	public int getShowID() {
		return showID;
	}

	public String getShowName() {
		return showName;
	}

	public int getShowAirport() {
		return showAirport;
	}

	public LocalDate getShowDate() {
		return showDate;
	}

	public void setShowID(int showID) {
		this.showID = showID;
	}

	public void setShowName(String showName) {
		this.showName = showName;
	}

	public void setShowAirport(int showAirport) {
		this.showAirport = showAirport;
	}

	public void setShowDate(LocalDate showDate) {
		
		this.showDate = showDate;
	}

	@Override
	public String toString() {
		return "AirShows [showID=" + showID + ", showName=" + showName + ", showAirport=" + showAirport + ", showDate="
				+ showDate + "]";
	}
	
	


}
