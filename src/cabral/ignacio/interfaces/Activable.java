package cabral.ignacio.interfaces;

import cabral.ignacio.Alarma;
import cabral.ignacio.Usuario;
import cabral.ignacio.excepciones.CodigoAlarmaIncorrectoException;
import cabral.ignacio.excepciones.UsuarioNoValidoException;

public interface Activable {
	public void activarAlarma(Alarma alarma, Usuario usuario, String codigoActivacionDesactivacion) throws CodigoAlarmaIncorrectoException, UsuarioNoValidoException;
}
