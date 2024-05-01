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

	public int agregarCliente(Cliente objeto) {
		int id = 0;
		
		try {
			iniciaOperacion();
			id = Integer.parseInt(session.save(objeto).toString());
			tx.commit();
		} catch (HibernateException e) {
			manejaExcepcion(e);
		} finally {
			session.close();
		}
		return id;
	}

	public void actualizar(Cliente objeto) {
		try {
			iniciaOperacion();
			session.update(objeto);
			tx.commit();
		} catch (HibernateException he) {
			manejaExcepcion(he);
		} finally {
			session.close();
		}
	}

	public void eliminar(Cliente objeto) {
		try {
			iniciaOperacion();
			session.delete(objeto);
			tx.commit();
		} catch (HibernateException e) {
			manejaExcepcion(e);
		} finally {
			session.close();
		}
	}

	public Cliente traer(long idCliente) {
		Cliente objeto = null;
		try {
			iniciaOperacion();
			objeto = (Cliente) session.get(Cliente.class, idCliente);
		} finally {
			session.close();
		}
		return objeto;
	}

	public Cliente traer(int dni) {
		Cliente cliente = null;
		try {
			iniciaOperacion();
			cliente = (Cliente) session.createQuery("from Cliente c where dni = :dni").setParameter("dni", dni)
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
			Query<Cliente> query = session.createQuery("from Cliente c order by c.apellido asc, c.nombre asc", Cliente.class);
			lista = query.getResultList();
		}finally {
			session.close();
		}
		return lista;
	}
	
	public Cliente traerClienteYContacto(long idCliente) {
		Cliente objeto = null;
		try {
			iniciaOperacion();
			String hql = "from Cliente c inner join fetch c.contacto where c.idCliente = :idCliente";
			objeto = (Cliente) session.createQuery(hql).setParameter("idCliente", idCliente).uniqueResult();
		}finally {
			session.close();
		}
		return objeto;
	}
}
