import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import com.google.gson.Gson;

public class Certificado {
	String fecha_expedicion;
	byte[] diploma;
	
	
	public Certificado(String fecha_de_expedicion, String path_diploma) throws IOException {
		this.fecha_expedicion = fecha_de_expedicion;
		
		byte[] inFileBytes = Files.readAllBytes(Paths.get(path_diploma)); 
		byte[] encoded = java.util.Base64.getEncoder().encode(inFileBytes);

		this.diploma = encoded;
	}
	
	public String toJson() {
		return new Gson().toJson(this);
	}
}
