package es.etg.dam.acceso.dao.oracle;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import es.etg.dam.acceso.dao.AlumnoDAO;
import es.etg.dam.acceso.model.Alumno;

public class AlumnoDAOOracleHibernate implements AlumnoDAO {
    @Override
    public void crearTabla() {
        // Hibernate gestiona esquema via hbm2ddl.auto=update, no necesario
    }

    @Override
    public int insertar(Alumno a) throws SQLException {
        Session session = ConexionOracleHibernate.obtenerConexion().getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.persist(a);
            tx.commit();
            return 1;
        } catch (Exception e) {
            if (tx != null)
                tx.rollback();
            throw new SQLException();
        } finally {
            session.close();
        }
    }

    @Override
    public int actualizar(Alumno a) throws SQLException {
        Session session = ConexionOracleHibernate.obtenerConexion().getSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.merge(a);
            tx.commit();
            return 1;
        } catch (Exception e) {
            if (tx != null)
                tx.rollback();
            throw new SQLException();
        } finally {
            session.close();
        }
    }

    @Override
    public List<Alumno> listarAll() {
        try (Session session = ConexionOracleHibernate.obtenerConexion().getSession()) {
            return session.createQuery("FROM Alumno", Alumno.class).list();
        }
    }

    @Override
    public List<String> listarRelacionados() {
        try (Session session = ConexionOracleHibernate.obtenerConexion().getSession()) {
            List<String> resultados = session.createNativeQuery("""
                    SELECT a.nombre || ' - ' || p.nombre AS alumno_tutor
                    FROM usuario.alumno a, usuario.profesor p
                    WHERE a.cod_tutor = p.cod_prof
                    ORDER BY a.nombre
                    """).getResultList();

            return resultados;
        }
    }

    @Override
    public Alumno obtenerAlumno(Long id) {
        try (Session session = ConexionOracleHibernate.obtenerConexion().getSession()) {
            return session.get(Alumno.class, id);
        }
    }
}
