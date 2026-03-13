package es.etg.dam.acceso.dao.oracle;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ConexionOracleHibernate {
    private static volatile ConexionOracleHibernate instance;
    private final SessionFactory sessionFactory;

    private ConexionOracleHibernate() {
        Configuration config = new Configuration().configure("es/etg/dam/acceso/hibernate.cfg.xml");
        sessionFactory = config.buildSessionFactory();
    }

    public static ConexionOracleHibernate obtenerConexion() {
        if (instance == null) {
            synchronized (ConexionOracleHibernate.class) {
                instance = new ConexionOracleHibernate();
            }
        }
        return instance;
    }

    public Session getSession() {
        return sessionFactory.openSession();
    }

    public void cerrar() {
        sessionFactory.close();
    }
}
