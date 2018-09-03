package model.Dto;

import java.io.Serializable;
import javax.persistence.*;
import java.sql.Time;
import java.util.Date;


/**
 * The persistent class for the registro database table.
 * 
 */
@Entity
@NamedQuery(name="Registro.findAll", query="SELECT r FROM Registro r")
public class Registro implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer idregistro;

	private Integer estado;

	@Temporal(TemporalType.DATE)
	private Date fecha;

	private Time hora;

	private double humedad;

	private double temperatura;

	//bi-directional many-to-one association to Ciudad
	@ManyToOne
	@JoinColumn(name="fk_registro_ciudad")
	private Ciudad ciudad;

	public Registro() {
	}

	public Integer getIdregistro() {
		return this.idregistro;
	}

	public void setIdregistro(Integer idregistro) {
		this.idregistro = idregistro;
	}

	public Integer getEstado() {
		return this.estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public Date getFecha() {
		return this.fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Time getHora() {
		return this.hora;
	}

	public void setHora(Time hora) {
		this.hora = hora;
	}

	public double getHumedad() {
		return this.humedad;
	}

	public void setHumedad(double humedad) {
		this.humedad = humedad;
	}

	public double getTemperatura() {
		return this.temperatura;
	}

	public void setTemperatura(double temperatura) {
		this.temperatura = temperatura;
	}

	public Ciudad getCiudad() {
		return this.ciudad;
	}

	public void setCiudad(Ciudad ciudad) {
		this.ciudad = ciudad;
	}

}