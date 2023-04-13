import multichain.command.*;
import multichain.object.*;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.*;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


public class Multichain {
	public static String host = "localhost";
	public static String port = "6762";
	public static String rpcuser = "multichainrpc";
	public static String rpcpasswd = "5Vdi7mhPQUpa761DAE2zbwATV8VHef3Yi5KbgpumEPtY";
	
	public static String insertarGraduando(String pasaporte, String nombre, String apellidos, String fecha_de_nacimiento, String celular) {
		CommandManager commandManager = new CommandManager(host, port, rpcuser, rpcpasswd);
		List<StreamKeyItem> items;
		
		try {
			commandManager.invoke(CommandElt.SUBSCRIBE, "Graduandos");
			commandManager.invoke(CommandElt.SETRUNTIMEPARAM, "maxshowndata",131072);
			items = (List<StreamKeyItem>) commandManager.invoke(CommandElt.LISTSTREAMITEMS, "Graduandos");
			
			for (StreamKeyItem item : items) {
				if (item.getKeys().get(0).equals(pasaporte)) {
					return "El pasaporte ya existe";
				}
			}
			
			
		} catch (MultichainException e) {
			return e.getReason();
		}
		
		try {
			Graduando elemento = new Graduando(nombre, apellidos, fecha_de_nacimiento, celular);
			JsonData datos = new JsonData(elemento.toJson());
			commandManager.invoke(CommandElt.SETRUNTIMEPARAM, "maxshowndata",131072);
			commandManager.invoke(CommandElt.PUBLISH, "Graduandos", pasaporte, datos);
			
		} catch (MultichainException e) {
			return e.getReason();
		}
		
		return "Elemento publicado correctamente";
	}
	
	
	public static String insertarCertificado(String pasaporte, String programa, String fecha_de_expedicion, String path_diploma) {
		CommandManager commandManager = new CommandManager(host, port, rpcuser, rpcpasswd);
		List<StreamKeyItem> items;
		
		try {
			commandManager.invoke(CommandElt.SUBSCRIBE, "Graduandos");
			commandManager.invoke(CommandElt.SETRUNTIMEPARAM, "maxshowndata",131072);
			items = (List<StreamKeyItem>) commandManager.invoke(CommandElt.LISTSTREAMITEMS, "Graduandos");
			
			boolean existe = false;
			
			for (StreamKeyItem item : items) {
				if (item.getKeys().get(0).equals(pasaporte)) {
					existe = true;
					break;
				}
			}
			
			if (!existe) {
				return "El pasaporte del graduando no existe";
			}
			
			
		} catch (MultichainException e) {
			return e.getReason();
		}
		
		try {
			Certificado elemento = new Certificado(fecha_de_expedicion, path_diploma);
			JsonData datos = new JsonData(elemento.toJson());
			String[] keys = {pasaporte, programa};
			commandManager.invoke(CommandElt.SETRUNTIMEPARAM, "maxshowndata",131072);
			commandManager.invoke(CommandElt.PUBLISH, "Certificados", keys , datos);
			
		} catch(MultichainException e) {
			return e.getMessage();
					
		} catch(IOException e) {
			return "Por favor ingresa una ruta válida";
		}
		
		return "Elemento publicado correctamente";
	}
	
	
	
	public static List<StreamKeyItem> listarCertificados(String pasaporte, String programa, String ruta) {
		CommandManager commandManager = new CommandManager(host, port, rpcuser, rpcpasswd);

		List<StreamKeyItem> items;
		List<StreamKeyItem> returnItems = new ArrayList<StreamKeyItem>();

		try {
			commandManager.invoke(CommandElt.SUBSCRIBE, "Certificados");
			commandManager.invoke(CommandElt.SETRUNTIMEPARAM, "maxshowndata",131072);
			items = (List<StreamKeyItem>) commandManager.invoke(CommandElt.LISTSTREAMITEMS, "Certificados");
			
			
			if (!pasaporte.equals("") && !programa.equals("")) {
				for (StreamKeyItem item : items) {
					if (item.getKeys().get(0).equals(pasaporte) && item.getKeys().get(1).equals(programa)) {
						
						returnItems.add(item);
					}
				}
			} else {
				for (StreamKeyItem item : items) {
					if (item.getKeys().get(0).equals(pasaporte) || item.getKeys().get(1).equals(programa)) {
						returnItems.add(item);
					}
				}
			}
			
		} catch (MultichainException e) {
			e.getMessage();
		}
		int index = 0;
		for (StreamKeyItem item : returnItems) {
			JsonData jsonObject = new Gson().fromJson(item.getData().toString(), JsonData.class);
			Certificado certificado = new Gson().fromJson(jsonObject.json, Certificado.class);
			
			byte[] decoded = java.util.Base64.getDecoder().decode(certificado.diploma);
			FileOutputStream fos;
			
			String path = ruta+"\\"+item.getKeys().get(0)+"_"+item.getKeys().get(1)+"_"+index+".pdf";
			
			try {
				fos = new FileOutputStream(path);
				fos.write(decoded);
				fos.flush();
				fos.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
			index++;
		}
		
		return returnItems;
	}
}
