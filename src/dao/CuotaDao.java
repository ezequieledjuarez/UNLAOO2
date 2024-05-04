package dao;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import datos.Cuota;

public class CuotaDao {

	private static Session session;
	private static Transaction tx;

	private void iniciaOperacion() throws HibernateException {
		session = HibernateUtil.getSessionFactory().openSession();
		tx = session.beginTransaction();
	}

	private void manejaExcepcion(HibernateException he) throws HibernateException {
		tx.rollback();
		throw new HibernateException("ERROR en la capa de acceso: " + he);
	}

	public int agregarCuota(Cuota cuota) {
		int id = 0;
		
		try {
			iniciaOperacion();
			id = Integer.parseInt(session.save(cuota).toString());
			tx.commit();
		}catch(HibernateException he) {
			manejaExcepcion(he);
		}finally {
			session.close();
		}
		
		return id;
	}
	
	public Cuota traerCuota(int idCuota,int nroCuota) {
		Cuota cuotaBuscada =  null;
		try {
			iniciaOperacion();
			cuotaBuscada= (Cuota) session.createQuery("from cuota where idCuota=:idCuota and nroCuota=:nroCuota")
					.setParameter("idCuota", idCuota)
					.setParameter("nroCuota", nroCuota).uniqueResult();
		}finally {
			session.close();
		}
		
		return cuotaBuscada;
	}

}
