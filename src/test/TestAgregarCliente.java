package test;

import java.time.LocalDate;

import org.hibernate.HibernateException;

import datos.Cliente;
import negocio.ClienteABM;
import negocio.ContactoABM;

public class TestAgregarCliente {
	public static void main(String[] args) throws Exception {

		ClienteABM abm = new ClienteABM();
		ContactoABM abmContacto = new ContactoABM();
		
		long ultimoIdCliente = 0L;
		
		try {
			ultimoIdCliente = abm.agregar("Eze", "Juarez", 38147191, LocalDate.of(1994, 3, 8));
		} catch (HibernateException he) {
			throw he;
		}

		System.out.printf("Id cliente: %d", ultimoIdCliente);
		/*
		 * En el formateo de Strings con printf tiene varios especificadores de formato.
		 * Algunos de los más comunes son: %d: Entero con signo (para int y long). %f:
		 * Número de punto flotante (para float y double). %s: Cadena de caracteres. %c:
		 * Carácter. %b: Valor booleano.
		 */
		
		try {
			Cliente cliente = abm.traer(38147191);
			abmContacto.agregar("eze@unla.com", "11141411", "42141144", cliente);
		}catch (Exception e) {
			throw e;
		}
		
		
		try {
			Cliente cliente = abm.traer(38147191);
			abmContacto.agregar("eze@unla.com", "11141411", "42141144", cliente);
		}catch (Exception e) {
			throw e;
		}
	}
}
