package cabral.ignacio;

import java.util.Date;

import cabral.ignacio.enumeradores.TipoOperacion;
import cabral.ignacio.excepciones.CodigoAlarmaIncorrectoException;
import cabral.ignacio.excepciones.SensorDuplicadoException;
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
	public void agregarSensorAAlarma(Alarma alarma, String string, Usuario usuario, Sensor sensor)
			throws SensorDuplicadoException {
		if (!(alarma.getSensores().add(sensor))) {
			throw new SensorDuplicadoException("Ya existe este sensor en esta alarma");
		} else {
			alarma.getAccionesRealizadas().add(new Accion(usuario, new Date(), TipoOperacion.CONFIGURACION));
		}
	}

	@Override
	public void activarSensorDeAlarma() {
	}

	@Override
	public void activarDesactivar() {

	}

}
