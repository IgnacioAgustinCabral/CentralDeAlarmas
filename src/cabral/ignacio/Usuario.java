package cabral.ignacio;

public class Usuario {
	private String nombre;
	private Integer DNI;

	public Usuario(Integer DNI, String nombre) {
		this.DNI = DNI;
		this.nombre = nombre;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Integer getDNI() {
		return DNI;
	}

	public void setDNI(Integer dNI) {
		DNI = dNI;
	}

}
