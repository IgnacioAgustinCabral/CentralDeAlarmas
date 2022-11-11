package cabral.ignacio.interfaces;

import cabral.ignacio.Alarma;
import cabral.ignacio.Central;
import cabral.ignacio.Sensor;
import cabral.ignacio.Usuario;
import cabral.ignacio.excepciones.CodigoAlarmaIncorrectoException;
import cabral.ignacio.excepciones.SensorDuplicadoException;
import cabral.ignacio.excepciones.SensorNoEncontradoEnAlarmaException;

public interface Configurable {

	public void agregarUsuarioAUnaAlarma(Alarma alarma, Usuario usuario, String codigoConfiguracion)
			throws CodigoAlarmaIncorrectoException;

	public void agregarSensorAAlarma(Alarma alarma, Usuario usuario,String codigoConfiguracion,  Sensor sensor)
			throws SensorDuplicadoException, CodigoAlarmaIncorrectoException;

	public void activarSensorDeAlarma(Alarma alarma, Usuario usuario, String codigoConfiguracion, Sensor sensor)
			throws SensorNoEncontradoEnAlarmaException, CodigoAlarmaIncorrectoException;
}
