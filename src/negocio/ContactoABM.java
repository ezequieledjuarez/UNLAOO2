
package negocio;

import dao.ClienteDao;
import dao.ContactoDao;
import datos.Cliente;
import datos.Contacto;

public class ContactoABM {
	ContactoDao dao = new ContactoDao();
	ClienteDao clienteDao = new ClienteDao();
	
	public Contacto traer(long idContacto) {
		Contacto c = dao.traer(idContacto);
		return c;
	}
	
	public int agregar(String email, String movil, String fijo, Cliente cliente) throws Exception {
		Cliente cl = clienteDao.traer(cliente.getDni());
		
		if(null != cl && null != cliente.getContacto()) {
			throw new Exception("El cliente ya tiene un contacto asignado");
		}else {
			return dao.agregar(new Contacto(email, movil, fijo, cliente));
			
		}
		
	}
	
	public void modificar(Contacto c) throws Exception {
		Contacto aModificar = dao.traer(c.getIdContacto());
	
		if(!existeEmail(c) || null == aModificar) 
			throw new Exception("El contacto no pudo ser actualizado");
		else
			dao.actualizarObjeto(c);
	}
	
	private boolean existeEmail(Contacto c) {
		Contacto aBuscar = dao.traer(c.getEmail());
		
		return c.getIdContacto() != aBuscar.getIdContacto();
	}
	
	public void eliminar(long idCliente) throws Exception {
		Contacto c = traer(idCliente);
		if(null == c)
			throw new Exception("El contacto que se intenta eliminar no existe");
		else
			dao.eliminar(c);
	}

}
