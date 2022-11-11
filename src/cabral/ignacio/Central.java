package cabral.ignacio;

import java.util.HashSet;
import java.util.Set;

public class Central {

	private Usuario usuario;
	private Set<Alarma> registroDeAlarmas = new HashSet<Alarma>();
	private Set<Usuario> registroDeUsuarios = new HashSet<Usuario>();
	public Central() {
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Set<Alarma> getRegistroDeAlarmas() {
		return registroDeAlarmas;
	}

	public void setRegistroDeAlarmas(Set<Alarma> registroDeAlarmas) {
		this.registroDeAlarmas = registroDeAlarmas;
	}

	public Set<Usuario> getRegistroDeUsuarios() {
		return registroDeUsuarios;
	}

	public void setRegistroDeUsuarios(Set<Usuario> registroDeUsuarios) {
		this.registroDeUsuarios = registroDeUsuarios;
	}
	

}
