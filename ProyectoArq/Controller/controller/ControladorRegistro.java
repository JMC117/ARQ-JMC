package controller;

import java.util.Calendar;

import model.Dao.CiudadDao;
import model.Dao.RegistroDao;
import model.Dto.Ciudad;
import model.Dto.Registro;

public class ControladorRegistro {

	public boolean CrearReg(double hume, double temp) {
		boolean bool;
		Registro reg = new Registro();
		RegistroDao regDao = new RegistroDao();
		Ciudad ciudad = new Ciudad();
		CiudadDao ciudadDao = new CiudadDao();
		java.sql.Timestamp fecha = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
		java.sql.Time hora = new java.sql.Time(Calendar.getInstance().getTime().getTime());
		
		reg.setIdregistro(regDao.consultarId());
		reg.setHumedad(hume);
		reg.setTemperatura(temp);
		reg.setFecha(fecha);
		reg.setHora(hora);
		ciudad = ciudadDao.Select(1);
		reg.setCiudad(ciudad);
		reg.setEstado(1);
		bool = regDao.create(reg);

		return bool;

	}

	
}
