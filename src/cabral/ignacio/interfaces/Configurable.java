package cabral.ignacio.interfaces;

import cabral.ignacio.Alarma;
import cabral.ignacio.Central;
import cabral.ignacio.Sensor;
import cabral.ignacio.Usuario;
import cabral.ignacio.excepciones.CodigoAlarmaIncorrectoException;
import cabral.ignacio.excepciones.SensorDuplicadoException;

public interface Configurable {

	public void activarSensorDeAlarma();

	public void agregarUsuarioAUnaAlarma(Alarma alarma, Usuario usuario, String codigoConfiguracion)
			throws CodigoAlarmaIncorrectoException;

	public void agregarSensorAAlarma(Alarma alarma, String string, Usuario usuario, Sensor sensor)
			throws SensorDuplicadoException;
}
