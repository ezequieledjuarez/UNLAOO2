package test;

import datos.Cliente;
import negocio.ClienteABM;

public class TestCliente {

	public static void main(String[] args) {

		int idCliente=1;
		System.out.printf("+traer(%d)\n", idCliente);
		System.out.println(ClienteABM.getInstance().traerCliente(idCliente));
		idCliente=2;
		System.out.printf("+traer(%d)\n", idCliente);
		System.out.println(ClienteABM.getInstance().traerCliente(idCliente));
		
		System.out.println("\n+ traer()");
		
		for (Cliente cliente : ClienteABM.getInstance().traerListaClientes()) {
			System.out.println(cliente);
		}
	}

}
