package es.home.example.knowledge.pojo;

public class Saludo {

	private String saludo;
	private String nombre;

	public Saludo() {
		super();
	}

	public Saludo(final String saludo, final String nombre) {
		this.saludo = saludo;
		this.nombre = nombre;
	}

	public String getSaludo() {
		return saludo;
	}

	public void setSaludo(final String saludo) {
		this.saludo = saludo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(final String nombre) {
		this.nombre = nombre;
	}
}
