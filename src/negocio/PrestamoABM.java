package negocio;

import java.time.LocalDate;
import java.util.List;

import dao.PrestamoDao;
import datos.Cliente;
import datos.Prestamo;

public class PrestamoABM {

	private PrestamoDao prestamoDao = new PrestamoDao();
	
	public Prestamo traer(long idPrestamo) {
		return prestamoDao.traer(idPrestamo);
	}
	
	public List<Prestamo> traer(Cliente c){
		return prestamoDao.traer(c);
	}
	
	public int agregar(LocalDate fecha, double monto, double interes, int cantCuotas, Cliente cliente) throws Exception {
		if(!prestamoDao.traer(cliente).isEmpty()) {
			throw new Exception("El cliente tiene un préstamo vigente");
		}
		Prestamo p = new Prestamo(fecha, monto, interes, cantCuotas, cliente);
		return prestamoDao.agregar(p);
	}
	
	public void modificar(Prestamo p) throws Exception {
		Prestamo prestamoAModificar = prestamoDao.traer(p.getIdPrestamo());
		
		if(prestamoAModificar == null)
			throw new Exception("El préstamo que se intenta modificar no existe");
		else
		 prestamoDao.modificar(p);
	}
}
