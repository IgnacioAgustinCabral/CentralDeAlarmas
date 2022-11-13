package cabral.ignacio;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import cabral.ignacio.enumeradores.TipoOperacion;
import cabral.ignacio.excepciones.CodigoAlarmaIncorrectoException;
import cabral.ignacio.excepciones.SensorDuplicadoException;
import cabral.ignacio.excepciones.SensorNoEncontradoEnAlarmaException;
import cabral.ignacio.excepciones.UsuarioNoValidoException;

public class AlarmaTest {
	Integer idAlarma = 320;
	String codigoActDesact = "mono";
	String codigoConfiguracion = "qwerty123";
	String nombre = "alarma vecinal";

	@Test
	public void queSePuedaRegistrarUnaAlarmaEnLaCentral() {

		Central central = new Central();

		Usuario admin = new Administrador(123, "Pedro");

		Alarma alarma = new Alarma(idAlarma, codigoActDesact, codigoConfiguracion, nombre);

		((Administrador) admin).agregarAlarma(central, alarma);

		assertEquals(1, central.getRegistroDeAlarmas().size());
	}

	@Test
	public void queSePuedaAgregarUnUsuarioConfiguradorAUnaAlarma() {
		Central central = new Central();

		Usuario admin = new Administrador(123, "Pedro");

		Usuario configurador = new Configurador(456, "Juan");

		Usuario activador = new Activador(122, "Luis");

		((Administrador) admin).agregarUsuario(central, configurador);
		((Administrador) admin).agregarUsuario(central, activador);

		assertEquals(2, central.getRegistroDeUsuarios().size());

	}

	@Test(expected = CodigoAlarmaIncorrectoException.class)
	public void alAgregarUnUsuarioAUnaAlarmaConCodigoDeConfiguracionDeAlarmaInvalidoSeLanceCodigoAlarmaIncorrectoException()
			throws CodigoAlarmaIncorrectoException {
		Central central = new Central();

		Usuario admin = new Administrador(123, "Pedro");

		Usuario configurador = new Configurador(456, "Juan");

		Usuario activador = new Activador(122, "Luis");

		Alarma alarma = new Alarma(idAlarma, codigoActDesact, codigoConfiguracion, nombre);

		((Administrador) admin).agregarAlarma(central, alarma);

		((Administrador) admin).agregarUsuarioAUnaAlarma(alarma, configurador, "qwerty123");

		((Administrador) admin).agregarUsuarioAUnaAlarma(alarma, activador, "qwerty321");
	}

	@Test(expected = SensorDuplicadoException.class)
	public void alAgregarUnSensorDuplicadoEnUnaAlarmaSeLanceUnaSensorDuplicadoException()
			throws SensorDuplicadoException, CodigoAlarmaIncorrectoException {

		Central central = new Central();

		Usuario admin = new Administrador(123, "Pedro");

		Alarma alarma = new Alarma(idAlarma, codigoActDesact, codigoConfiguracion, nombre);

		Integer id = 3333;
		Sensor sensor1 = new Sensor(id);
		Sensor sensor2 = new Sensor(id);

		((Administrador) admin).agregarSensorAAlarma(alarma, admin, "qwerty123", sensor1);
		((Administrador) admin).agregarSensorAAlarma(alarma, admin, "qwerty123", sensor2);

	}

	@Test
	public void queSePuedanActivarSensores() {

		Central central = new Central();

		Usuario admin = new Administrador(123, "Pedro");

		Alarma alarma = new Alarma(idAlarma, codigoActDesact, codigoConfiguracion, nombre);

		Integer id = 3333;
		Sensor sensor1 = new Sensor(id);

		try {
			((Administrador) admin).agregarSensorAAlarma(alarma, admin, "qwerty123", sensor1);
		} catch (SensorDuplicadoException e) {
			e.printStackTrace();
		} catch (CodigoAlarmaIncorrectoException e) {
			e.printStackTrace();
		}

		try {
			((Administrador) admin).activarSensorDeAlarma(alarma, admin, "qwerty123", sensor1);
		} catch (Exception e) {
			e.printStackTrace();
		}

		assertTrue(sensor1.getEstado());

	}

	@Test(expected = SensorNoEncontradoEnAlarmaException.class)
	public void queAlQuererActivarUnSensorQueNoSeEncuentraEnLaAlarmaLanceSensorNoEncontradoEnAlarmaException()
			throws SensorNoEncontradoEnAlarmaException, CodigoAlarmaIncorrectoException {

		Central central = new Central();

		Usuario admin = new Administrador(123, "Pedro");

		Alarma alarma = new Alarma(idAlarma, codigoActDesact, codigoConfiguracion, nombre);

		Integer id = 3333;
		Sensor sensor1 = new Sensor(id);
		Sensor sensor2 = new Sensor(id + 1);

		try {
			((Administrador) admin).agregarSensorAAlarma(alarma, admin, "qwerty123", sensor1);
		} catch (SensorDuplicadoException e) {
			e.printStackTrace();
		} catch (CodigoAlarmaIncorrectoException e) {
			e.printStackTrace();
		}

		((Administrador) admin).activarSensorDeAlarma(alarma, admin, "qwerty123", sensor2);

	}

	@Test
	public void queNoSePuedaActivarUnaAlarmaSiHayAlMenosUnSensorDesactivado() {

		Central central = new Central();

		Usuario configurador = new Configurador(456, "Juan");

		Usuario activador = new Activador(122, "Luis");

		Alarma alarma = new Alarma(idAlarma, codigoActDesact, codigoConfiguracion, nombre);

		Integer id = 3333;
		Sensor sensor1 = new Sensor(id);
		Sensor sensor2 = new Sensor(id + 1);

		try {
			((Configurador) configurador).agregarUsuarioAUnaAlarma(alarma, activador, codigoConfiguracion);
		} catch (CodigoAlarmaIncorrectoException e) {
			e.printStackTrace();
		}

		try {
			((Configurador) configurador).agregarSensorAAlarma(alarma, configurador, codigoConfiguracion, sensor1);
		} catch (SensorDuplicadoException e) {
			e.printStackTrace();
		} catch (CodigoAlarmaIncorrectoException e) {
			e.printStackTrace();
		}

		try {
			((Configurador) configurador).agregarSensorAAlarma(alarma, configurador, codigoConfiguracion, sensor2);
		} catch (SensorDuplicadoException e1) {
			e1.printStackTrace();
		} catch (CodigoAlarmaIncorrectoException e1) {
			e1.printStackTrace();
		}

		try {
			((Configurador) configurador).activarSensorDeAlarma(alarma, configurador, codigoConfiguracion, sensor1);
		} catch (SensorNoEncontradoEnAlarmaException e) {
			e.printStackTrace();
		} catch (CodigoAlarmaIncorrectoException e) {
			e.printStackTrace();
		}

		// el sensor no se activa por lo tanto no se puede activar la alarma
//		try {
//			((Configurador) configurador).activarSensorDeAlarma(alarma, configurador, codigoConfiguracion, sensor2);
//		} catch (SensorNoEncontradoEnAlarmaException e1) {
//			e1.printStackTrace();
//		} catch (CodigoAlarmaIncorrectoException e1) {
//			e1.printStackTrace();
//		}

		try {
			((Activador) activador).activarAlarma(alarma, activador, codigoActDesact);
		} catch (CodigoAlarmaIncorrectoException e) {
			e.printStackTrace();
		} catch (UsuarioNoValidoException e) {
			e.printStackTrace();
		}

		assertFalse(alarma.getActivadaDesactivada());
	}

	@Test
	public void queParaUnaAlarmaDeterminadaSePuedaObtenerUnaColeccionOrdenadaDeAccionesDeTipoConfiguracionOdenadasPorIdDeAccion() {

		Central central = new Central();

		Usuario configurador = new Configurador(456, "Juan");

		Usuario activador = new Activador(122, "Luis");

		Alarma alarma = new Alarma(idAlarma, codigoActDesact, codigoConfiguracion, nombre);

		Integer id = 3333;
		Sensor sensor1 = new Sensor(id);
		Sensor sensor2 = new Sensor(id + 1);

		try {
			((Configurador) configurador).agregarUsuarioAUnaAlarma(alarma, activador, codigoConfiguracion);
		} catch (CodigoAlarmaIncorrectoException e) {
			e.printStackTrace();
		}

		try {
			((Configurador) configurador).agregarSensorAAlarma(alarma, configurador, codigoConfiguracion, sensor1);
		} catch (SensorDuplicadoException e) {
			e.printStackTrace();
		} catch (CodigoAlarmaIncorrectoException e) {
			e.printStackTrace();
		}

		try {
			((Configurador) configurador).agregarSensorAAlarma(alarma, configurador, codigoConfiguracion, sensor2);
		} catch (SensorDuplicadoException e1) {
			e1.printStackTrace();
		} catch (CodigoAlarmaIncorrectoException e1) {
			e1.printStackTrace();
		}

		try {
			((Configurador) configurador).activarSensorDeAlarma(alarma, configurador, codigoConfiguracion, sensor1);
		} catch (SensorNoEncontradoEnAlarmaException e) {
			e.printStackTrace();
		} catch (CodigoAlarmaIncorrectoException e) {
			e.printStackTrace();
		}

		try {
			((Configurador) configurador).activarSensorDeAlarma(alarma, configurador, codigoConfiguracion, sensor2);
		} catch (SensorNoEncontradoEnAlarmaException e1) {
			e1.printStackTrace();
		} catch (CodigoAlarmaIncorrectoException e1) {
			e1.printStackTrace();
		}

		try {
			((Activador) activador).activarAlarma(alarma, activador, codigoActDesact);
		} catch (CodigoAlarmaIncorrectoException e) {
			e.printStackTrace();
		} catch (UsuarioNoValidoException e) {
			e.printStackTrace();
		}
		
		for (Accion accion : alarma.getOperacionesDeConfiguracion()) {
			System.out.println(accion.getId());
		}
		
	}
}
