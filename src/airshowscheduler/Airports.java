package airshowscheduler;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "airports")

public class Airports {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "AirPortID")
	private int airportID;
	
	@Column(name = "AirportName")
	private String airportName;
	
	@Column(name = "AirportRunwayLength")
	private int airportRunwayLen;
	
	@Column(name = "AirportFuelAvailable")
	private String airportFuel;
	
	@Column(name = "AirportCode")
	private String airportCode;
	
	public Airports() {}
	
	public Airports(int airportID, String apName, int apRunLen, String apFuel, String apCode) {
		this.setAirportID(airportID);
		this.setAirportCode(apCode);
		this.setAirportName(apName);
		this.setAirportFuel(apFuel);
		this.setAirportRunwayLen(apRunLen);
	}
	
	public Airports(String apName, int apRunLen, String apFuel, String apCode) {
		this.setAirportCode(apCode);
		this.setAirportName(apName);
		this.setAirportFuel(apFuel);
		this.setAirportRunwayLen(apRunLen);
	}

	public int getAirportID() {
		return airportID;
	}

	public String getAirportName() {
		return airportName;
	}

	public int getAirportRunwayLen() {
		return airportRunwayLen;
	}

	public String getAirportFuel() {
		return airportFuel;
	}

	public String getAirportCode() {
		return airportCode;
	}

	public void setAirportID(int airportID) {
		this.airportID = airportID;
	}
	
	public void setAirportName(String airportName) {
		this.airportName = airportName;
	}

	public void setAirportRunwayLen(int airportRunwayLen) {
		this.airportRunwayLen = airportRunwayLen;
	}

	public void setAirportFuel(String airportFuel) {
		this.airportFuel = airportFuel;
	}

	public void setAirportCode(String airportCode) {
		this.airportCode = airportCode;
	}

	@Override
	public String toString() {
		return "Airports [airportID=" + airportID + ", airportName=" + airportName + ", airportRunwayLen="
				+ airportRunwayLen + ", airportFuel=" + airportFuel + ", airportCode=" + airportCode + "]";
	}
	
	
}
