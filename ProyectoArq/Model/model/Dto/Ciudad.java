package model.Dto;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Set;


/**
 * The persistent class for the ciudad database table.
 * 
 */
@Entity
@NamedQuery(name="Ciudad.findAll", query="SELECT c FROM Ciudad c")
public class Ciudad implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer idciudad;

	private Integer estado;

	private String nombre;

	//bi-directional many-to-one association to Registro
	@OneToMany(mappedBy="ciudad")
	private Set<Registro> registros;

	public Ciudad() {
	}

	public Integer getIdciudad() {
		return this.idciudad;
	}

	public void setIdciudad(Integer idciudad) {
		this.idciudad = idciudad;
	}

	public Integer getEstado() {
		return this.estado;
	}

	public void setEstado(Integer estado) {
		this.estado = estado;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Set<Registro> getRegistros() {
		return this.registros;
	}

	public void setRegistros(Set<Registro> registros) {
		this.registros = registros;
	}

	public Registro addRegistro(Registro registro) {
		getRegistros().add(registro);
		registro.setCiudad(this);

		return registro;
	}

	public Registro removeRegistro(Registro registro) {
		getRegistros().remove(registro);
		registro.setCiudad(null);

		return registro;
	}

}