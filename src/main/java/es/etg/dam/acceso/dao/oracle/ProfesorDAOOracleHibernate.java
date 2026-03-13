package es.etg.dam.acceso.dao.oracle;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import es.etg.dam.acceso.dao.ProfesorDAO;
import es.etg.dam.acceso.model.Profesor;

public class ProfesorDAOOracleHibernate implements ProfesorDAO {
    public ProfesorDAOOracleHibernate() {
    }

    @Override
    public void crearTabla() throws SQLException {
        // Hibernate gestiona con hbm2ddl.auto, opcional DDL nativo
    }

    @Override
    public int insertar(Profesor p) throws SQLException {
        Session session = ConexionOracleHibernate.obtenerConexion().getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.persist(p);
            tx.commit();
            return 1;
        } catch (Exception e) {
            if (tx != null)
                tx.rollback();
            throw new SQLException("Error insertar Profesor", e);
        } finally {
            session.close();
        }
    }

    @Override
    public int actualizar(Profesor p) throws SQLException {
        Session session = ConexionOracleHibernate.obtenerConexion().getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.merge(p);
            tx.commit();
            return 1;
        } catch (Exception e) {
            if (tx != null)
                tx.rollback();
            throw new SQLException("Error actualizar Profesor", e);
        } finally {
            session.close();
        }
    }

    @Override
    public List<Profesor> listarAll() throws SQLException {
        try (Session session = ConexionOracleHibernate.obtenerConexion().getSession()) {
            return session.createQuery("FROM Profesor", Profesor.class).list();
        } catch (Exception e) {
            throw new SQLException("Error listar Profesores", e);
        }
    }

    @Override
    public Profesor obtenerProfesor(Long id) throws SQLException {
        try (Session session = ConexionOracleHibernate.obtenerConexion().getSession()) {
            Profesor p = session.get(Profesor.class, id);
            return p;
        } catch (Exception e) {
            throw new SQLException("Error obtener Profesor " + id, e);
        }
    }
}
