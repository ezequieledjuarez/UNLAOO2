package test;

import datos.Cliente;
import datos.PersonaFisica;
import negocio.ClienteABM;

public class TestAgregarCliente {

	public static void main(String[] args) {

		Cliente personaFisica = new PersonaFisica("7741274", "Eze", "Juarez", 44188890);
		System.out.println("+agregar\n" + personaFisica);
		try {
			System.out.println(ClienteABM.getInstance().agregarCliente(personaFisica));
		} catch (Exception e) {
			System.out.println(e);
		}
		
		
		for (Cliente cliente : ClienteABM.getInstance().traerListaClientes()) {
			System.out.println(cliente);
		}
	}

}
