package test;

import org.hibernate.HibernateException;

import negocio.ClienteABM;
import negocio.ContactoABM;

public class TestEliminarContacto {
	public static void main(String[] args) throws Exception {

		ContactoABM abm = new ContactoABM();
		
		try {
			abm.eliminar(6L);
			System.out.printf("El contacto ha sido eliminado con éxito");
		} catch (HibernateException he) {
			throw he;
		}

		
		/*
		 * En el formateo de Strings con printf tiene varios especificadores de formato.
		 * Algunos de los más comunes son: %d: Entero con signo (para int y long). %f:
		 * Número de punto flotante (para float y double). %s: Cadena de caracteres. %c:
		 * Carácter. %b: Valor booleano.
		 */

	}
}
