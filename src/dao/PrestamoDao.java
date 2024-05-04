package dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import datos.Cliente;
import datos.Prestamo;

public class PrestamoDao {

	private static Session session;
	private Transaction tx;
	
	private void iniciaOperacion()throws HibernateException{
		session=HibernateUtil.getSessionFactory().openSession();
		tx=session.beginTransaction();
	}
	
	private void manejaExcepcion(HibernateException he)throws HibernateException{
		tx.rollback();
		throw new HibernateException("Error en la capa de accesos de datos", he);
	}
	
	public Prestamo traerSinCliente(long idObjeto) {
		Prestamo objeto = null;
		try {
			iniciaOperacion();
			objeto = (Prestamo) session.get(Prestamo.class, idObjeto);
		}finally {
			session.close();
		}
		return objeto;
	}
	
	public Prestamo traer(long idPrestamo) {
		Prestamo objeto = null;
		try {
			iniciaOperacion();
			String hql="from Prestamo p inner join fetch p.cliente c where p.idPrestamo=:idPrestamo";
			objeto=(Prestamo)session.createQuery(hql).setParameter("idPrestamo", idPrestamo).uniqueResult();
		}finally {
			session.close();
		}
		return objeto;
	}
	
	public List<Prestamo>traer(Cliente c){
		List<Prestamo> lista = null;
		try {
			iniciaOperacion();
			String hql = "from Prestamo p inner join fetch p.cliente c where c.idCliente=:idCliente";
			lista=session.createQuery(hql, Prestamo.class).setParameter("idCliente", c.getIdCliente()).getResultList();
		}finally {
			session.close();
		}
		return lista;
	}
	
	public int agregar(Prestamo objeto) {
		int id=0;
		try {
			iniciaOperacion();
			id=Integer.parseInt(session.save(objeto).toString());
			tx.commit();
		}catch (HibernateException he) {
			manejaExcepcion(he);
			throw he;
		}finally {
			session.close();
		}
		return id;
	}
	
	public void modificar(Prestamo objeto) {
		try {
			iniciaOperacion();
			session.update(objeto);
			tx.commit();
		}catch(HibernateException he) {
			manejaExcepcion(he);
			throw he;
		}finally {
			session.close();
		}
		
	}
}

