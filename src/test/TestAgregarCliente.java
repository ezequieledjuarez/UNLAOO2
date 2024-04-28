package test;

import java.time.LocalDate;

import org.hibernate.HibernateException;

import negocio.ClienteABM;

public class TestAgregarCliente {
	public static void main(String[] args) throws Exception {

		ClienteABM abm = new ClienteABM();
		long ultimoIdCliente = 0L;
		
		try {
			ultimoIdCliente = abm.agregar("Apellido", "Nombre", 35000000, LocalDate.now());
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

	}
}
