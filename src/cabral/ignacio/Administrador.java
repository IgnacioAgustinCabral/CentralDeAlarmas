package cabral.ignacio;

import java.util.Date;

import cabral.ignacio.enumeradores.TipoOperacion;
import cabral.ignacio.excepciones.AlarmaYaRegistradaException;
import cabral.ignacio.excepciones.CodigoAlarmaIncorrectoException;
import cabral.ignacio.excepciones.SensorDuplicadoException;
import cabral.ignacio.excepciones.SensorNoEncontradoEnAlarmaException;
import cabral.ignacio.excepciones.UsuarioNoValidoException;
import cabral.ignacio.excepciones.UsuarioYaRegistradoException;
import cabral.ignacio.interfaces.Activable;
import cabral.ignacio.interfaces.Configurable;

public class Administrador extends Usuario implements Configurable, Activable {

	public Administrador(Integer DNI, String nombre) {
		super(DNI, nombre);
	}

	public void agregarAlarma(Central central, Alarma alarma) throws AlarmaYaRegistradaException {
		if(!central.getRegistroDeAlarmas().add(alarma)) {
			throw new AlarmaYaRegistradaException("Esta alarma ya esta registrada en la central");
		}
	}

	public void agregarUsuario(Central central, Usuario usuario) throws UsuarioYaRegistradoException {
		if(!central.getRegistroDeUsuarios().add(usuario)) {
			throw new UsuarioYaRegistradoException("Este usuario ya esta registrado en la central");
		}
		
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
	public void activarAlarma(Alarma alarma, Usuario usuario, String codigoActivacionDesactivacion)
			throws CodigoAlarmaIncorrectoException,UsuarioNoValidoException {
		// TODO Auto-generated method stub
		
	}

}
