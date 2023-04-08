import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;

import com.google.gson.Gson;

public class Certificado {
	String fecha_de_expedicion;
	String diploma;
	
	
	public Certificado(String fecha_de_expedicion, String path_diploma) throws IOException {
		this.fecha_de_expedicion = fecha_de_expedicion;
		
		byte[] inFileBytes = Files.readAllBytes(Paths.get(path_diploma)); 
		String encoded = java.util.Base64.getEncoder().encodeToString(inFileBytes);

		this.diploma = encoded;
	}
	
	public String toJson() {
		return new Gson().toJson(this);
	}
}
