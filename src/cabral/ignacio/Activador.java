package cabral.ignacio;

import java.util.Date;

import cabral.ignacio.enumeradores.TipoOperacion;
import cabral.ignacio.excepciones.CodigoAlarmaIncorrectoException;
import cabral.ignacio.excepciones.UsuarioNoValidoException;
import cabral.ignacio.interfaces.Activable;

public class Activador extends Usuario implements Activable {

	public Activador(Integer DNI, String nombre) {
		super(DNI, nombre);
	}

	@Override
	public void activarAlarma(Alarma alarma, Usuario usuario, String codigoActivacionDesactivacion)
			throws CodigoAlarmaIncorrectoException, UsuarioNoValidoException {
		if (alarma.getUsuariosValidos().contains(usuario)) {
			if (alarma.getCodigoActDesact().equals(codigoActivacionDesactivacion)) {
				for (Sensor sensor : alarma.getSensores()) {
					if (sensor.getEstado().equals(Boolean.TRUE)) {
						alarma.setActivadaDesactivada(Boolean.TRUE);
					} else {
						alarma.setActivadaDesactivada(Boolean.FALSE);
					}
				}
				if (alarma.getActivadaDesactivada().equals(Boolean.TRUE)) {
					alarma.getAccionesRealizadas().add(new Accion(usuario, new Date(), TipoOperacion.ACTIVACION));
				}

			} else {
				throw new CodigoAlarmaIncorrectoException("Codigo de configuración incorrecto");
			}
		} else {
			throw new UsuarioNoValidoException("Este usuario no tiene permitido activar o desactivar la alarma");
		}
	}

}
