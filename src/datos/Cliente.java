package datos;

import java.time.LocalDate;
import java.util.Objects;

public class Cliente {

	private long idCliente;
	private String apellido;
	private String nombre;
	private int dni;
	private LocalDate fechaDeNacimiento;
	private boolean baja;
	private Contacto contacto;

	public Cliente() {
		super();
	}

	public Cliente(String apellido, String nombre, int dni, LocalDate fechaDeNacimiento, Contacto contacto) {
		super();
		this.apellido = apellido;
		this.nombre = nombre;
		this.dni = dni;
		this.fechaDeNacimiento = fechaDeNacimiento;
		this.contacto = contacto;
	}

	public Cliente(String apellido, String nombre, int dni, LocalDate fechaDeNacimiento) {
		super();
		this.apellido = apellido;
		this.nombre = nombre;
		this.dni = dni;
		this.fechaDeNacimiento = fechaDeNacimiento;
	}

	public long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(long idCliente) {
		this.idCliente = idCliente;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}

	public LocalDate getFechaDeNacimiento() {
		return fechaDeNacimiento;
	}

	public void setFechaDeNacimiento(LocalDate fechaDeNacimiento) {
		this.fechaDeNacimiento = fechaDeNacimiento;
	}

	public boolean isBaja() {
		return baja;
	}

	public void setBaja(boolean baja) {
		this.baja = baja;
	}

	public Contacto getContacto() {
		return contacto;
	}

	public void setContacto(Contacto contacto) {
		this.contacto = contacto;
	}

	@Override
	public int hashCode() {
		return Objects.hash(apellido, baja, dni, fechaDeNacimiento, idCliente, nombre);
	}

	public boolean equals(Cliente cliente) {
		return cliente.getDni() == this.dni && cliente.getNombre().equalsIgnoreCase(this.nombre)
				&& cliente.getApellido().equalsIgnoreCase(this.apellido)
				&& cliente.getFechaDeNacimiento().equals(this.getFechaDeNacimiento());

	}

	@Override
	public String toString() {
		return "Cliente [idCliente=" + idCliente + ", apellido=" + apellido + ", nombre=" + nombre + ", dni=" + dni
				+ ", fechaDeNacimiento=" + fechaDeNacimiento + ", baja=" + baja +",contacto: " + contacto + "]";
	}

	public Cliente modificar(Cliente cliente, String nombre, String apellido, int dni, LocalDate fechaDeNacimiento) {
		Cliente clienteNuevo = new Cliente(apellido, nombre, dni, fechaDeNacimiento);
		clienteNuevo.setIdCliente(cliente.getIdCliente());

		return clienteNuevo;
	}

	public Cliente modificar(Cliente cliente, String nombre, String apellido, int dni, LocalDate fechaDeNacimiento,
			Contacto contacto) {
		Cliente clienteNuevo = new Cliente(apellido, nombre, dni, fechaDeNacimiento, contacto);
		clienteNuevo.setIdCliente(cliente.getIdCliente());

		return clienteNuevo;
	}

}
