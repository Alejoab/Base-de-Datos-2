import multichain.command.*;
import multichain.object.*;

import java.util.*;

import com.google.gson.Gson;


public class Multichain {
	public static String host = "localhost";
	public static String port = "9716";
	public static String rpcuser = "multichainrpc";
	public static String rpcpasswd = "7vacR4shXKKFMYDtQn4BG3Gts28GMepAeMik1LQqCp6f";
	public static String stream = "Graduandos";
	
	public static String insertarGraduando(String pasaporte, String nombre, String apellidos, String fecha_de_nacimiento, String celular) {
		CommandManager commandManager = new CommandManager(host, port, rpcuser, rpcpasswd);
		List<StreamKeyItem> items;
		
		try {
			commandManager.invoke(CommandElt.SUBSCRIBE, "Graduandos");
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
			commandManager.invoke(CommandElt.PUBLISH, "Certificados", keys , datos);

			
		} catch (Exception e) {
			return e.getMessage();
		}
		
		return "Elemento publicado correctamente";
	}
	
	
	
	public static List<StreamKeyItem> listarCertificados(String pasaporte, String programa) {
		CommandManager commandManager = new CommandManager(host, port, rpcuser, rpcpasswd);

		List<StreamKeyItem> items;
		List<StreamKeyItem> returnItems = new ArrayList<StreamKeyItem>();

		try {
			commandManager.invoke(CommandElt.SUBSCRIBE, "Certificados");
			items = (List<StreamKeyItem>) commandManager.invoke(CommandElt.LISTSTREAMITEMS, "Certificados");
			
			
			if (!pasaporte.equals("") && !programa.equals("")) {
				for (StreamKeyItem item : items) {
					if (item.getKeys().get(0).equals(pasaporte) && item.getKeys().get(1).equals(programa)) {
						
						returnItems.add(item);
					}
				}
			} else {
				for (StreamKeyItem item : items) {
					System.out.println(item);
					if (item.getKeys().get(0).equals(pasaporte) || item.getKeys().get(1).equals(programa)) {
						
						returnItems.add(item);
					}
				}
			}
			
		} catch (MultichainException e) {
			e.getMessage();
		}
		
		return returnItems;
	}
}
