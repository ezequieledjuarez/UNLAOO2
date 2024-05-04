package datos;

import java.time.LocalDate;
import java.util.Objects;

public class Cuota {

	private int idCuota;
	private int nroCuota;
	private LocalDate fechaVencimiento;
	private double saldoPendiente;
	private double amortizacion;
	private double interesCuota;
	private double valorCuota;
	private double deuda;
	private boolean cancelada;
	private LocalDate fechaDePago;
	private double punitorios;
	Prestamo prestamo;

	public Cuota() {
		super();
	}

	public Cuota(int nroCuota, LocalDate fechaVencimiento, double saldoPendiente, double amortizacion,
			double interesCuota, double couta, double deuda, boolean cancelada, LocalDate fechaDePago,
			double punitorios, Prestamo prestamo) {
		super();
		this.nroCuota = nroCuota;
		this.fechaVencimiento = fechaVencimiento;
		setSaldoPendiente(prestamo);
		setAmortizacion(prestamo);
		setInteresCuota(prestamo);
		setValorCuota();
		setDeuda();
		this.cancelada = cancelada;
		this.fechaDePago = fechaDePago;
		this.punitorios = punitorios;
	}

	
	
	private void setDeuda() {
		this.deuda = this.saldoPendiente -this.amortizacion;
		
	}

	private void setValorCuota() {
		this.valorCuota = this.amortizacion + this.interesCuota;
		
	}

	private void setInteresCuota(Prestamo prestamo) {
		this.interesCuota = saldoPendiente * prestamo.getInteres();
		
	}

	private void setAmortizacion(Prestamo prestamo) {
		
		double exponente = 1 == nroCuota 
				? prestamo.getCantCuotas()
				: prestamo.getCantCuotas() - nroCuota -1;
		
		double numerador = saldoPendiente * prestamo.getInteres();
		double denominador = Math.pow(1+prestamo.getInteres(), exponente)-1;
		
		this.amortizacion = numerador/denominador;
		
	}

	private void setSaldoPendiente(Prestamo prestamo) {
		if (1 == nroCuota)
			this.saldoPendiente = prestamo.getMonto();
		else
			this.saldoPendiente = prestamo.getMonto() - this.amortizacion;
	}

	public int getIdCuota() {
		return idCuota;
	}

	public void setIdCuota(int idCuota) {
		this.idCuota = idCuota;
	}

	public int getNroCuota() {
		return nroCuota;
	}

	public void setNroCuota(int nroCuota) {
		this.nroCuota = nroCuota;
	}

	public LocalDate getFechaVencimiento() {
		return fechaVencimiento;
	}

	public void setFechaVencimiento(LocalDate fechaVencimiento) {
		this.fechaVencimiento = fechaVencimiento;
	}

	public double getSaldoPendiente() {
		return saldoPendiente;
	}

	public void setSaldoPendiente(double saldoPendiente) {
		this.saldoPendiente = saldoPendiente;
	}

	public double getAmortizacion() {
		return amortizacion;
	}

	public void setAmortizacion(double amortizacion) {
		this.amortizacion = amortizacion;
	}

	public double getInteresCuota() {
		return interesCuota;
	}

	public void setInteresCuota(double interesCuota) {
		this.interesCuota = interesCuota;
	}

	public double getDeuda() {
		return deuda;
	}

	public void setDeuda(double deuda) {
		this.deuda = deuda;
	}

	public boolean isCancelada() {
		return cancelada;
	}

	public void setCancelada(boolean cancelada) {
		this.cancelada = cancelada;
	}

	public LocalDate getFechaDePago() {
		return fechaDePago;
	}

	public void setFechaDePago(LocalDate fechaDePago) {
		this.fechaDePago = fechaDePago;
	}

	public double getPunitorios() {
		return punitorios;
	}

	public void setPunitorios(double punitorios) {
		this.punitorios = punitorios;
	}

	public double getValorCuota() {
		return valorCuota;
	}

	public void setValorCuota(double cuota) {
		this.valorCuota = cuota;
	}

	public Prestamo getPrestamo() {
		return prestamo;
	}

	public void setPrestamo(Prestamo prestamo) {
		this.prestamo = prestamo;
	}

	@Override
	public String toString() {
		return "Cuota [nroCuota=" + nroCuota + ", fechaVencimiento=" + fechaVencimiento + ", saldoPendiente="
				+ saldoPendiente + ", amortizacion=" + amortizacion + ", interesCuota=" + interesCuota + ", deuda="
				+ deuda + ", cancelada=" + cancelada + ", fechaDePago=" + fechaDePago + ", punitorios=" + punitorios
				+ "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(amortizacion, cancelada, deuda, fechaDePago, fechaVencimiento, interesCuota, nroCuota,
				punitorios, saldoPendiente);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cuota other = (Cuota) obj;
		return Double.doubleToLongBits(amortizacion) == Double.doubleToLongBits(other.amortizacion)
				&& cancelada == other.cancelada
				&& Double.doubleToLongBits(deuda) == Double.doubleToLongBits(other.deuda)
				&& Objects.equals(fechaDePago, other.fechaDePago)
				&& Objects.equals(fechaVencimiento, other.fechaVencimiento)
				&& Double.doubleToLongBits(interesCuota) == Double.doubleToLongBits(other.interesCuota)
				&& nroCuota == other.nroCuota
				&& Double.doubleToLongBits(punitorios) == Double.doubleToLongBits(other.punitorios)
				&& Double.doubleToLongBits(saldoPendiente) == Double.doubleToLongBits(other.saldoPendiente);
	}

}
