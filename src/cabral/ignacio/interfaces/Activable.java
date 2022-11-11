package cabral.ignacio.interfaces;

import cabral.ignacio.Alarma;
import cabral.ignacio.Usuario;
import cabral.ignacio.excepciones.CodigoAlarmaIncorrectoException;

public interface Activable {
	public void activarDesactivar(Alarma alarma, Usuario usuario, String codigoActivacionDesactivacion) throws CodigoAlarmaIncorrectoException;
}
