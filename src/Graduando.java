import com.google.gson.Gson;

public class Graduando {
	String nombre;
	String apellidos;
	String fecha_nacimiento;
	String celular;
	
	
	public Graduando(String nombre, String apellidos, String fecha_de_nacimiento, String celular) {
		this.nombre = nombre;
		this.apellidos = apellidos;
		this.fecha_nacimiento = fecha_de_nacimiento;
		this.celular = celular;
	}
	
	public String toJson() {
		return new Gson().toJson(this);
	}
}
