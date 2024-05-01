package negocio;

import java.time.LocalDate;
import java.util.List;

import dao.ClienteDao;
import dao.ContactoDao;
import datos.Cliente;
import datos.Contacto;

public class ClienteABM {

	ClienteDao dao = new ClienteDao();
	ContactoDao contactoDAO = new ContactoDao();
	
	public Cliente traer(long idCliente) {
		return dao.traer(idCliente);
	}

	public Cliente traer(int dni) {
		return dao.traer(dni);
	}

	public int agregar(String nombre, String apellido, int dni, LocalDate fechaDeNacimiento, Contacto contacto) throws Exception {
		List<Cliente> listaClientes = traer();
		int iterador = 0;
		Cliente c = new Cliente(apellido, nombre, dni, fechaDeNacimiento, contacto);
		// consultar si existe un cliente con el mismo dni, y si existe, arrojar la
		// Excepcion

		while (!listaClientes.isEmpty() && iterador < listaClientes.size()) {
			if (esMismoDNI(listaClientes.get(iterador), dni))
				throw new Exception("El cliente que se intenta agregar ya existe en la base de datos");
			else
				iterador++;
		}

		return dao.agregarCliente(c);
	}
	
	public int agregar(String nombre, String apellido, int dni, LocalDate fechaDeNacimiento) throws Exception {
		List<Cliente> listaClientes = traer();
		int iterador = 0;
		Cliente c = new Cliente(apellido, nombre, dni, fechaDeNacimiento);
		// consultar si existe un cliente con el mismo dni, y si existe, arrojar la
		// Excepcion

		while (!listaClientes.isEmpty() && iterador < listaClientes.size()) {
			if (esMismoDNI(listaClientes.get(iterador), dni))
				throw new Exception("El cliente que se intenta agregar ya existe en la base de datos");
			else
				iterador++;
		}

		return dao.agregarCliente(c);
	}

	public void modificarCliente(Cliente c) throws Exception {
		/*
		 * En caso de editar el dni, antes de actualizar, validar que no exista un
		 * cliente con el mismo dni y si eso pasa lanzar la Exception
		 */
		Cliente clienteAModificar = traer(c.getDni());

		if (null == clienteAModificar || c.getIdCliente() == clienteAModificar.getIdCliente())
			dao.actualizar(c);
		else
			throw new Exception("El dni del cliente que se intenta modificar ya existe en la base de datos");

	}

	public void eliminar(long idCliente) throws Exception {
		// Implementar que si es null que arroje la excepción la Excepción de que el
		// cliente no existe
		Cliente c = dao.traer(idCliente);
		
		if (null == c)
			throw new Exception("El id del cliente que se intenta eliminar no existe en la base de datos");
		
		if(c.getContacto() == null) {
			dao.eliminar(c);
		}else {
			contactoDAO.eliminar(c.getContacto());
		}
			

			dao.eliminar(c);
	}

	public List<Cliente> traer() {
		return dao.traer();
	}

	private boolean esMismoDNI(Cliente cliente, int dni) {
		return cliente.getDni() == dni;
	}
}
