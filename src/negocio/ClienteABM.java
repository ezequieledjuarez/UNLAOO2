package negocio;

import java.util.List;

import dao.ClienteDao;
import datos.Cliente;
import datos.PersonaFisica;
import datos.PersonaJuridica;

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

	public int agregarCliente(Cliente cliente) throws Exception {

		boolean clienteValido = false;
		String mensaje = "";
		if (cliente instanceof PersonaFisica) {
			clienteValido = validarPersonaFisica((PersonaFisica) cliente);
			mensaje="La razon social que se intenta agregar ya está en uso";
		}
			
		else {
			clienteValido = validarPersonaJuridica((PersonaJuridica) cliente);
			mensaje="El dni que se intenta agregar ya está en uso";
		}
			

		if(!clienteValido)
			throw new Exception("No se puede agregar el cliente");
		
		
		return clienteDao.agregarCliente(cliente);
	}

	private boolean validarPersonaJuridica(PersonaJuridica cliente) throws Exception {
		String razonSocial = cliente.getRazonSocial();
		boolean existeRazonSocial = false;
		int i = 0;
		List<Cliente> listaClientes = traerListaClientes();

		while (listaClientes != null && !existeRazonSocial && i <= listaClientes.size()) {

			if (listaClientes.get(i) instanceof PersonaJuridica) {
				existeRazonSocial = validarRazonSocial(razonSocial, (PersonaJuridica) listaClientes.get(i));
			} else {
				i++;
			}

		}

		return existeRazonSocial;
	}

	private boolean validarRazonSocial(String razonSocial, PersonaJuridica cliente) {
		return cliente.getRazonSocial().equalsIgnoreCase(razonSocial);
	}

	private boolean validarPersonaFisica(PersonaFisica cliente) throws Exception {
		// TODO Auto-generated method stub

	}
}
