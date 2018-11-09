package model.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
//import org.hibernate.cfg.Configuration;

public abstract class Dao<T> {
	static SessionFactory sessionFactory;
	static Session session;

	public Dao() {
		Configuration con = new Configuration();
		con.configure("hibernate.cfg.xml");
		sessionFactory = con.buildSessionFactory();

	}

	public static void openSession() {
		try {
			session = sessionFactory.openSession();
			session.beginTransaction();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void closeSession() {
		try {
			session.beginTransaction().commit();
			session.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public abstract void create(T entityClass);

	public abstract T retrieve(int id);

	public abstract List<T> retrieveAll();

	public abstract void update(T entityClass);

	public abstract void delete(int id);
}