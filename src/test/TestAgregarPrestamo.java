package test;

import java.time.LocalDate;

import datos.Cliente;
import negocio.ClienteABM;
import negocio.PrestamoABM;

public class TestAgregarPrestamo {
	public static void main(String[] args) throws Exception {

		PrestamoABM prestamoABM = new PrestamoABM();
		ClienteABM clienteABM = new ClienteABM();

		Cliente cliente = clienteABM.traer(38147191);

		try {
			prestamoABM.agregar(LocalDate.now(), 10000, 10, 6, cliente);
		} catch (Exception e) {
			throw e;
		}
		
		
	}
}