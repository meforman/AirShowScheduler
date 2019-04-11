package airshowscheduler;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "performances")

public class Performances {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PerformanceId")
	private int perfID;
	
	@Column(name = "PerformanceAirShowID")
	private int perfAirShowId;
	
	@Column(name = "PerformanceActID")
	private int perfActId;
	
	public Performances() {};
	
	public Performances(int showId, int actId) {
		this.setPerfAirShowId(showId);
		this.setPerfActId(actId);
	}
	
	public Performances(int perfID, int showId, int actId) {
		this.setPerfID(perfID);
		this.setPerfAirShowId(showId);
		this.setPerfActId(actId);
	}

	public int getPerfID() {
		return perfID;
	}

	public int getPerfAirShowId() {
		return perfAirShowId;
	}

	public int getPerfActId() {
		return perfActId;
	}

	public void setPerfID(int perfID) {
		this.perfID = perfID;
	}

	public void setPerfAirShowId(int perfAirShowId) {
		this.perfAirShowId = perfAirShowId;
	}

	public void setPerfActId(int perfActId) {
		this.perfActId = perfActId;
	}

	@Override
	public String toString() {
		return "Performances [perfID=" + perfID + ", perfAirShowId=" + perfAirShowId + ", perfActId=" + perfActId + "]";
	}
	
	
	
}