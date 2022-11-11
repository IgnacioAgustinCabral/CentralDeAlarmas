package cabral.ignacio;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

public class Alarma {

	private Integer idAlarma;
	private String codigoActDesact;
	private String codigoConfiguracion;
	private String nombre;
	private Set<Usuario> usuariosValidos = new HashSet<Usuario>();
	private Set<Sensor> sensores = new HashSet<Sensor>();
	private List<Accion> accionesRealizadas = new ArrayList<Accion>();

	public Alarma(Integer idAlarma, String codigoActDesact, String codigoConfiguracion, String nombre) {
		this.idAlarma = idAlarma;
		this.codigoActDesact = codigoActDesact;
		this.codigoConfiguracion = codigoConfiguracion;
		this.nombre = nombre;
	}

	public Integer getIdAlarma() {
		return idAlarma;
	}

	public void setIdAlarma(Integer idAlarma) {
		this.idAlarma = idAlarma;
	}

	public String getCodigoActDesact() {
		return codigoActDesact;
	}

	public void setCodigoActDesact(String codigoActDesact) {
		this.codigoActDesact = codigoActDesact;
	}

	public String getCodigoConfiguracion() {
		return codigoConfiguracion;
	}

	public void setCodigoConfiguracion(String codigoConfiguracion) {
		this.codigoConfiguracion = codigoConfiguracion;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Set<Usuario> getUsuariosValidos() {
		return usuariosValidos;
	}

	public void setUsuariosValidos(Set<Usuario> usuariosValidos) {
		this.usuariosValidos = usuariosValidos;
	}

	public Set<Sensor> getSensores() {
		return sensores;
	}

	public void setSensores(Set<Sensor> sensores) {
		this.sensores = sensores;
	}

	public List<Accion> getAccionesRealizadas() {
		return accionesRealizadas;
	}

	public void setAccionesRealizadas(List<Accion> accionesRealizadas) {
		this.accionesRealizadas = accionesRealizadas;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idAlarma);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Alarma other = (Alarma) obj;
		return Objects.equals(idAlarma, other.idAlarma);
	}

}
