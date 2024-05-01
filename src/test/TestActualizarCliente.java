package test;

import java.time.LocalDate;

import datos.Cliente;
import negocio.ClienteABM;

public class TestActualizarCliente {
	public static void main(String[] args) throws Exception {

	ClienteABM abm = new ClienteABM();

	Cliente cliente = abm.traer(4L);
	
	Cliente clienteAModificar = cliente.modificar(cliente, "Juarez", "Ezequiel Eduardo", 58185812, LocalDate.of(1994, 8, 3));

	try{
		abm.modificarCliente(clienteAModificar);
		System.out.println("El cliente se modificó con éxito");
	}catch (Exception e) {
		throw e;
	}
}}
