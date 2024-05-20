package dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import datos.Cliente;

public class ClienteDao {

	private static Session session;
	private Transaction tx;

	private void iniciaOperacion() throws HibernateException {
		session = HibernateUtil.getSessionFactory().openSession();
		tx = session.beginTransaction();
	}

	private void manejaExcepcion(HibernateException he) throws HibernateException {
		tx.rollback();
		throw new HibernateException("ERROR en la capa de acceso: " + he);
	}

	public Cliente traer(int id) {
		Cliente cliente = null;
		try {
			iniciaOperacion();
			cliente = (Cliente) session.createQuery("from Cliente c where c.idCliente = :id").setParameter("id", id)
					.uniqueResult();
		} finally {
			session.close();
		}
		return cliente;
	}

	public List<Cliente> traer(){
		List<Cliente> lista = new ArrayList<>();
		try {
			iniciaOperacion();
			lista= session.createQuery("from Cliente", Cliente.class).list();
			
		}finally {
			session.close();
		}
		return lista;
	}
	
	public int agregarCliente(Cliente cliente) {
		int id = 0;
		try {
			iniciaOperacion();
			id = Integer.parseInt(session.save(cliente).toString());
			tx.commit();
		}catch (HibernateException e) {
			manejaExcepcion(e);
		}finally {
			session.close();
		}
		return id;
	}

}
