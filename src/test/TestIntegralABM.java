package test;

import java.time.LocalDate;
import java.util.List;

import org.hibernate.HibernateException;

import datos.Cliente;
import negocio.ClienteABM;

public class TestIntegralABM {

	public static void main(String[] args) throws Exception {
		ClienteABM abm = new ClienteABM();
		long ultimoIdCliente = 0L;

		try {
			ultimoIdCliente = abm.agregar("Juan", "Perez", 35000011, LocalDate.now());
		} catch (HibernateException he) {
			throw he;
		}

		System.out.printf("Se agregó un cliente con id: %d", ultimoIdCliente);
		System.out.println();
		
		try {
			ultimoIdCliente = abm.agregar("Pedro", "Gonzalez", 36000020, LocalDate.now());
		} catch (HibernateException he) {
			throw he;
		}

		List<Cliente> clientes = abm.traer();

		System.out.println();
		System.out.println("Se listará la lista de clientes");
		for (Cliente cliente : clientes) {
			System.out.println(cliente);
		}

		System.out.printf("Se agregó un cliente con id: %d", ultimoIdCliente);
		System.out.println();
		
		Cliente cliente = abm.traer(15L);

		Cliente clienteAModificar = cliente.modificar(cliente, "Juarez", "Ezequiel Eduardo", 58185812,
				LocalDate.of(1994, 8, 3));

		try {
			abm.modificarCliente(clienteAModificar);
			System.out.println("El cliente se modificó con éxito");
		} catch (Exception e) {
			throw e;
		}

		try {
			abm.eliminar(14L);
			System.out.printf("El cliente ha sido eliminado con éxito");
		} catch (HibernateException he) {
			throw he;
		}

		clientes = abm.traer();

		System.out.println("Se listará la lista de clientes");
		for (Cliente cliente2 : clientes) {
			System.out.println(cliente2);
		}
	}

}
