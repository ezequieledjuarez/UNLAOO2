package test;

import org.hibernate.HibernateException;

import datos.Cliente;
import negocio.ClienteABM;
import negocio.ContactoABM;

public class TestAgregarContacto {
	public static void main(String[] args) throws Exception {

		ClienteABM abm = new ClienteABM();
		ContactoABM abmContacto = new ContactoABM();
		
		Cliente cliente = null;
		try {
			cliente = abm.traer(38147191);
		} catch (HibernateException he) {
			throw he;
		}

		try {
			abmContacto.agregar("ezejuarez@unla.com.ar", "11250159159", "422014050", cliente);
			System.out.println("Se agregó el contacto al cliente con éxito");
		}catch (Exception e) {
			throw e;
		}
		
		
		
	}
}
