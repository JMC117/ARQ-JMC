package controller;

import model.Dao.CiudadDao;
import model.Dto.Ciudad;

public class ControladorCiudad {

	public boolean CrearCiudad(int id, String nombre, int state) {
		boolean bool;
		Ciudad ciudad = new Ciudad();
		CiudadDao ciudadDao = new CiudadDao();

		ciudad.setIdciudad(id);
		ciudad.setNombre(nombre);
		ciudad.setEstado(state);

		bool = ciudadDao.Create(ciudad);

		return bool;
	}

	public Ciudad SelectCiu(int id) {
		Ciudad ciudad = new Ciudad();
		CiudadDao ciudadDao = new CiudadDao();
		ciudad=ciudadDao.Select(id);
		
		return ciudad;
	}
	
}
