package airshowscheduler;


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
@Table(name = "airshowacts")

public class AirShowActs  {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ActID")
	private int actID;
	
	@Column(name = "ActName")
	private String actName;
	
	@Column(name = "AirportRunwayRequired")
	private int actRunway;
	
	@Column(name = "ActFuelType")
	private String actFuel;
	
	@Column(name = "ActCost")
	private int actCost;
	
	public AirShowActs () {};
	
	public AirShowActs (String actname, int actrun, String actfuel, int actcost) {
		this.setActName(actname);
		this.setActRunway(actrun);
		this.setActFuel(actfuel);
		this.setActCost(actcost);
	}
	
	public AirShowActs (int actId, String actname, int actrun, String actfuel, int actcost) {
		this.setActID(actId);
		this.setActName(actname);
		this.setActRunway(actrun);
		this.setActFuel(actfuel);
		this.setActCost(actcost);
	}

	public int getActID() {
		return actID;
	}

	public String getActName() {
		return actName;
	}

	public int getActRunway() {
		return actRunway;
	}

	public String getActFuel() {
		return actFuel;
	}

	public int getActCost() {
		return actCost;
	}

	public void setActID(int actID) {
		this.actID = actID;
	}

	public void setActName(String actName) {
		this.actName = actName;
	}

	public void setActRunway(int actRunway) {
		this.actRunway = actRunway;
	}

	public void setActFuel(String actFuel) {
		this.actFuel = actFuel;
	}

	public void setActCost(int actCost) {
		this.actCost = actCost;
	}

	@Override
	public String toString() {
		return "AirShowActs [actID=" + actID + ", actName=" + actName + ", actRunway=" + actRunway + ", actFuel="
				+ actFuel + ", actCost=" + actCost + "]";
	}
	
	
}
