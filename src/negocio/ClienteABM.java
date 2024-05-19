package negocio;

import java.util.List;

import dao.ClienteDao;
import datos.Cliente;

public class ClienteABM {

	ClienteDao clienteDao = new ClienteDao();
	private static ClienteABM instancia = null; // Patrón Singleton

	public static ClienteABM getInstance() {
		if (instancia == null)
		instancia = new ClienteABM();
		return instancia;
		}

	
	public Cliente traerCliente(int idCliente) {
		return clienteDao.traer(idCliente);
	}
	
	public List<Cliente> traerListaClientes() {
		return clienteDao.traer();
	}
}
