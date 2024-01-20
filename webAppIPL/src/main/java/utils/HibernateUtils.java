package utils;

import org.hibernate.SessionFactory;;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {
	private static SessionFactory sf;
	static {
		try {
			sf = new Configuration().configure().buildSessionFactory();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static SessionFactory getSf() {
		return sf;
	}

}
