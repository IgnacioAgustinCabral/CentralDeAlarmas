package cabral.ignacio;

import java.util.Date;

import cabral.ignacio.enumeradores.TipoOperacion;
import cabral.ignacio.excepciones.CodigoAlarmaIncorrectoException;
import cabral.ignacio.interfaces.Activable;

public class Activador extends Usuario implements Activable {

	public Activador(Integer DNI,String nombre) {
		super(DNI,nombre);
	}

	@Override
	public void activarDesactivar(Alarma alarma, Usuario usuario, String codigoActivacionDesactivacion) throws CodigoAlarmaIncorrectoException {
		if(alarma.getUsuariosValidos().contains(usuario)) {
			if(alarma.getCodigoActDesact().equals(codigoActivacionDesactivacion)) {
				for(Sensor sensor : alarma.getSensores()) {
					if(sensor.getEstado().equals(true)) {
						alarma.setActivadaDesactivada(Boolean.TRUE);
						alarma.getAccionesRealizadas().add(new Accion(usuario, new Date(), TipoOperacion.ACTIVACION));
					} else {
						alarma.getAccionesRealizadas().add(new Accion(usuario, new Date(), TipoOperacion.DESACTIVACION));
					}
				}
			} else {
				throw new CodigoAlarmaIncorrectoException("Codigo de configuración incorrecto");
			}
		}
	}

}
