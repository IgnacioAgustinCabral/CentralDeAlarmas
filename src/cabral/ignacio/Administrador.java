package cabral.ignacio;

import java.util.Date;

import cabral.ignacio.enumeradores.TipoOperacion;
import cabral.ignacio.excepciones.CodigoAlarmaIncorrectoException;
import cabral.ignacio.excepciones.SensorDuplicadoException;
import cabral.ignacio.excepciones.SensorNoEncontradoEnAlarmaException;
import cabral.ignacio.interfaces.Activable;
import cabral.ignacio.interfaces.Configurable;

public class Administrador extends Usuario implements Configurable, Activable {

	public Administrador(Integer DNI, String nombre) {
		super(DNI, nombre);
	}

	public void agregarAlarma(Central central, Alarma alarma) {
		central.getRegistroDeAlarmas().add(alarma);
	}

	public void agregarUsuario(Central central, Usuario usuario) {
		central.getRegistroDeUsuarios().add(usuario);
	}

	@Override
	public void agregarUsuarioAUnaAlarma(Alarma alarma, Usuario usuario, String codigoConfiguracion)
			throws CodigoAlarmaIncorrectoException {

		if (alarma.getCodigoConfiguracion().equals(codigoConfiguracion)) {
			alarma.getUsuariosValidos().add(usuario);
			alarma.getAccionesRealizadas().add(new Accion(usuario, new Date(), TipoOperacion.CONFIGURACION));
		} else {
			throw new CodigoAlarmaIncorrectoException("Codigo de configuración incorrecto");
		}

	}

	@Override
	public void agregarSensorAAlarma(Alarma alarma, Usuario usuario, String codigoConfiguracion, Sensor sensor)
			throws SensorDuplicadoException, CodigoAlarmaIncorrectoException {

		if (alarma.getCodigoConfiguracion().equals(codigoConfiguracion)) {
			if (!(alarma.getSensores().add(sensor))) {
				throw new SensorDuplicadoException("Ya existe este sensor en esta alarma");
			} else {
				alarma.getAccionesRealizadas().add(new Accion(usuario, new Date(), TipoOperacion.CONFIGURACION));
			}
		} else {
			throw new CodigoAlarmaIncorrectoException("Codigo de configuración incorrecto");
		}

	}

	@Override
	public void activarSensorDeAlarma(Alarma alarma, Usuario usuario, String codigoConfiguracion, Sensor sensor)
			throws SensorNoEncontradoEnAlarmaException, CodigoAlarmaIncorrectoException {
		if (alarma.getCodigoConfiguracion().equals(codigoConfiguracion)) {
			if(alarma.getSensores().contains(sensor)) {
				sensor.setEstado(Boolean.TRUE);
				alarma.getAccionesRealizadas().add(new Accion(usuario, new Date(), TipoOperacion.CONFIGURACION));
			} else {
				throw new SensorNoEncontradoEnAlarmaException("El sensor que quiere activar no se encuentra en esta alarma");
			}
		} else {
			throw new CodigoAlarmaIncorrectoException("Codigo de configuración incorrecto");
		}
		
	}

	@Override
	public void activarDesactivar(Alarma alarma, Usuario usuario, String codigoConfiguracion) {
		// TODO Auto-generated method stub
		
	}

}
