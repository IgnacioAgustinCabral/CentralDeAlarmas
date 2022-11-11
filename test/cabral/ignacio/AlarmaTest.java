package cabral.ignacio;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Test;

import cabral.ignacio.enumeradores.TipoOperacion;
import cabral.ignacio.excepciones.CodigoAlarmaIncorrectoException;
import cabral.ignacio.excepciones.SensorDuplicadoException;

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

	@Test
	public void alAgregarUnUsuarioAUnaAlarmaConCodigoDeConfiguracionDeAlarmaInvalidoSeLanceCodigoAlarmaIncorrectoException()
			/*throws CodigoAlarmaIncorrectoException */{
		Central central = new Central();

		Usuario admin = new Administrador(123, "Pedro");

		Usuario configurador = new Configurador(456, "Juan");

		Alarma alarma = new Alarma(idAlarma, codigoActDesact, codigoConfiguracion, nombre);
		Alarma alarma2 = new Alarma(2, "asd", "zzz", "alarma cocina");

		((Administrador) admin).agregarAlarma(central, alarma);

		try {
			((Administrador) admin).agregarUsuarioAUnaAlarma(alarma, configurador, "qwerty123");
		} catch (CodigoAlarmaIncorrectoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			((Administrador) admin).agregarUsuarioAUnaAlarma(alarma2, configurador, "zzz");
		} catch (CodigoAlarmaIncorrectoException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		for (Accion acc: alarma.getAccionesRealizadas()) {
			System.out.println(acc.getId());
		}

	}

	@Test(expected = SensorDuplicadoException.class)
	public void alAgregarUnSensorDuplicadoEnUnaAlarmaSeLanceUnaSensorDuplicadoException()
			throws SensorDuplicadoException {

		Central central = new Central();

		Usuario admin = new Administrador(123, "Pedro");

		Usuario configurador = new Configurador(456, "Juan");

		Alarma alarma = new Alarma(idAlarma, codigoActDesact, codigoConfiguracion, nombre);

		Integer id = 3333;
		Sensor sensor1 = new Sensor(id);
		Sensor sensor2 = new Sensor(id);

		((Administrador) admin).agregarSensorAAlarma(alarma, "qwerty123", configurador,sensor1);
		((Administrador) admin).agregarSensorAAlarma(alarma, "qwerty123", configurador,sensor1);

	}
	
//	@Test
//	public void queSePuedanActivarODesactivarSensores() {
//		
//		Central central = new Central();
//
//		Usuario admin = new Administrador(123, "Pedro");
//
//		Usuario configurador = new Configurador(456, "Juan");
//
//		Alarma alarma = new Alarma(idAlarma, codigoActDesact, codigoConfiguracion, nombre);
//	}
	
	@Test
	public void asd() {
		Usuario configurador = new Configurador(456, "Juan");
		
		
		for (int i = 0; i < 10; i++) {
			Accion acc = new Accion(configurador, new Date(), TipoOperacion.CONFIGURACION);
			System.out.println(acc.getId());
		}
	}
}
