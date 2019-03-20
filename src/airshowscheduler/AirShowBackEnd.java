package airshowscheduler;


import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


/**
 * ToDoList class contains methods to interact with the database to create, list
 * and delete the ToDos
 * 
 * @author Michael Forman CEN2045 - 13200 Professor Shaban 2/6/2019
 */
public class AirShowBackEnd {


	static Session session = null;
	static Transaction goTrans = null;

	public static void saveAirports(Airports theAirport) {
		SessionFactory factory = new Configuration().configure("/resources/hibernate.cfg.xml")
				.addAnnotatedClass(Airports.class).buildSessionFactory();
		Session session = factory.getCurrentSession();
		try {
			goTrans = session.beginTransaction();
			session.save(theAirport);
			goTrans.commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			factory.close();
		}
	}
	
	public static void saveActs(AirShowActs theAct) {
		SessionFactory factory = new Configuration().configure("/resources/hibernate.cfg.xml")
				.addAnnotatedClass(AirShowActs.class).buildSessionFactory();
		Session session = factory.getCurrentSession();
		try {
			goTrans = session.beginTransaction();
			session.save(theAct);
			goTrans.commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			factory.close();
		}
	}
	
	public static void saveShows(AirShows theShow) {
		SessionFactory factory = new Configuration().configure("/resources/hibernate.cfg.xml")
				.addAnnotatedClass(AirShows.class).buildSessionFactory();
		Session session = factory.getCurrentSession();
		try {
			goTrans = session.beginTransaction();
			session.save(theShow);
			goTrans.commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			factory.close();
		}
	}
	
	public static void savePerfs(Performances thePerf) {
		SessionFactory factory = new Configuration().configure("/resources/hibernate.cfg.xml")
				.addAnnotatedClass(Performances.class).buildSessionFactory();
		Session session = factory.getCurrentSession();
		try {
			goTrans = session.beginTransaction();
			session.save(thePerf);
			goTrans.commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		} finally {
			factory.close();
		}
	}

	public static List<Airports> listAirports() {
		SessionFactory factory = new Configuration().configure("/resources/hibernate.cfg.xml")
				.addAnnotatedClass(Airports.class).buildSessionFactory();
		Session session = factory.getCurrentSession();
		try {
			session.beginTransaction();
			List<Airports> theAirports = session.createQuery("from Airports").list();
			session.getTransaction().commit();
			return theAirports;
		} finally {
			factory.close();
		}
	}
	
	public static List<AirShowActs> listActs() {
		SessionFactory factory = new Configuration().configure("/resources/hibernate.cfg.xml")
				.addAnnotatedClass(AirShowActs.class).buildSessionFactory();
		Session session = factory.getCurrentSession();
		try {
			session.beginTransaction();
			List<AirShowActs> theActs = session.createQuery("from AirShowActs").list();
			session.getTransaction().commit();

			return theActs;
		} finally {
			factory.close();
		}
	}
	
	public static List<AirShowActs> listQualActs(int showID) {
		SessionFactory factory = new Configuration().configure("/resources/hibernate.cfg.xml")
				.addAnnotatedClass(AirShowActs.class).buildSessionFactory();
		Session session = factory.getCurrentSession();
		try {
			session.beginTransaction();
			List<AirShowActs> theActs = (List<AirShowActs>) session.createSQLQuery("CALL getQualActs(:aShowID)")
					.addEntity(AirShowActs.class).setParameter("aShowID", showID).list();
			session.getTransaction().commit();

			return theActs;
		} finally {
			factory.close();
		}
	}
	
	public static List<AirShows> listShows() {
		SessionFactory factory = new Configuration().configure("/resources/hibernate.cfg.xml")
				.addAnnotatedClass(AirShows.class).buildSessionFactory();
		Session session = factory.getCurrentSession();
		try {
			session.beginTransaction();
			List<AirShows> theShows = session.createQuery("from AirShows").list();
			session.getTransaction().commit();
			return theShows;
		} finally {
			factory.close();
		}
	}
	
	public static List<Performances> listPerfs() {
		SessionFactory factory = new Configuration().configure("/resources/hibernate.cfg.xml")
				.addAnnotatedClass(Performances.class).buildSessionFactory();
		Session session = factory.getCurrentSession();
		try {
			session.beginTransaction();
			List<Performances> thePerfs = session.createQuery("from Performances").list();
			session.getTransaction().commit();
			return thePerfs;
		} finally {
			factory.close();
		}
	}

	public static void deleteAirport(Airports[] airportArray, int userChoice) {
		int recordID = airportArray[userChoice - 1].getAirportID();
		SessionFactory factory = new Configuration().configure("/resources/hibernate.cfg.xml")
				.addAnnotatedClass(Airports.class).buildSessionFactory();
		Session session = factory.getCurrentSession();
		try {
			session.beginTransaction();
			session.createQuery("delete from Airports where AirPortID = " + recordID).executeUpdate();
			session.getTransaction().commit();
		} finally {
			factory.close();
		}
		
	}
	
	public static void deleteActs(AirShowActs[] actArray, int userChoice) {
		int recordID = actArray[userChoice - 1].getActID();
		SessionFactory factory = new Configuration().configure("/resources/hibernate.cfg.xml")
				.addAnnotatedClass(AirShowActs.class).buildSessionFactory();
		Session session = factory.getCurrentSession();
		try {
			session.beginTransaction();
			session.createQuery("delete from AirShowActs where ActID = " + recordID).executeUpdate();
			session.getTransaction().commit();
		} finally {
			factory.close();
		}
		
	}
	
	public static void deleteShows(AirShows[] showArray, int userChoice) {
		int recordID = showArray[userChoice - 1].getShowID();
		SessionFactory factory = new Configuration().configure("/resources/hibernate.cfg.xml")
				.addAnnotatedClass(AirShows.class).buildSessionFactory();
		Session session = factory.getCurrentSession();
		try {
			session.beginTransaction();
			session.createQuery("delete from AirShows where AirShowID = " + recordID).executeUpdate();
			session.getTransaction().commit();
		} finally {
			factory.close();
		}
		
	}
	
	public static void deletePerfs(Performances[] perfArray, int userChoice) {
		int recordID = perfArray[userChoice - 1].getPerfID();
		SessionFactory factory = new Configuration().configure("/resources/hibernate.cfg.xml")
				.addAnnotatedClass(Performances.class).buildSessionFactory();
		Session session = factory.getCurrentSession();
		try {
			session.beginTransaction();
			session.createQuery("delete from Performances where PerformanceId = " + recordID).executeUpdate();
			session.getTransaction().commit();
		} finally {
			factory.close();
		}
		
	}

}



