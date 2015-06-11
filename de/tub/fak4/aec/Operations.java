package de.tub.fak4.aec;

import java.util.ArrayList;

import edu.kit.aifb.dbe.hermes.Request;
import edu.kit.aifb.dbe.hermes.Response;
import edu.kit.aifb.dbe.hermes.Sender;

/**
 *
 *
 *
 */
public class Operations
		implements CRUD_Interface {
	
	// private static Operations operations = new Operations();
	
	public static boolean create(String host, int port, String key, ArrayList<String> value, int timeout) {

		Request request = new Request(key, "create", key);
		request.addItem(value);
		Sender sender = new Sender(host, port);
		Response response = sender.sendMessage(request, timeout);
		return response.responseCode();
	}
	
	@SuppressWarnings("unchecked")
	public static ArrayList<String> read(String host, int port, String key) {
		Request request = new Request(key, "read", key);
		Sender sender = new Sender(host, port);
		Response response = sender.sendMessage(request, 1000);

		/*
		 * if (CommType == "sync") { } else { b =
		 * sender.sendMessageAsync(request, operations); }
		 */
		// return CommType;
		if (response.responseCode()) {
			return (ArrayList<String>) response.getItems().get(0);
		} else {
			return null;
		}

		// System.out.println("get is called");
		
	}

	public static boolean update(String host, int port, String key, ArrayList<String> value, int timeout) {

		Request request = new Request(key, "update", key);
		request.addItem(value);
		Sender sender = new Sender(host, port);
		Response response = sender.sendMessage(request, timeout);
		return response.responseCode();
	}
	
	public static void delete(String host, int port, String key, int timeout) {
		Request req = new Request(key, "delete", key);
		Sender s = new Sender(host, port);
		s.sendMessage(req, timeout);
	}
	
}
