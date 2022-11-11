package cabral.ignacio;

import java.util.Date;
import java.util.Objects;

import cabral.ignacio.enumeradores.TipoOperacion;

public class Accion {
	private Integer id;
	private Usuario usuario;
	private Date fechaDeAccion;
	private TipoOperacion operacion;
	
	private static Integer contador = 0;

	public Accion(Usuario usuario, Date fechaDeAccion, TipoOperacion operacion) {
		super();
		this.setId(++contador);
		this.usuario = usuario;
		this.fechaDeAccion = fechaDeAccion;
		this.operacion = operacion;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Date getFechaDeAccion() {
		return fechaDeAccion;
	}

	public void setFechaDeAccion(Date fechaDeAccion) {
		this.fechaDeAccion = fechaDeAccion;
	}

	public TipoOperacion getOperacion() {
		return operacion;
	}

	public void setOperacion(TipoOperacion operacion) {
		this.operacion = operacion;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Accion other = (Accion) obj;
		return Objects.equals(id, other.id);
	}
	
	

}
