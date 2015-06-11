package de.tub.fak4.aec;

import java.util.ArrayList;

import org.apache.log4j.PropertyConfigurator;

/**
 * This class tests the CRUD operations
 *
 */
public class Client {
	
	static String host = "localhost";
	static int port = 9010;
	private static int timeout = 1000;
	
	public static void main(String[] args) {
		PropertyConfigurator.configure("src/resources/log4j.properties");
		ArrayList<String> list = new ArrayList<String>();
		list.add("nesrine");
		list.add("yi");
		list.add("cloud");
		
		System.out.println("Running test ");

		Operations.create(host, port, "key", list, timeout);
		ArrayList<String> read = Operations.read(host, port, "key");
		System.out.println("The value is" + read);
		ArrayList<String> newlist = new ArrayList<String>();
		newlist.add("doghri");
		newlist.add("yang");
		newlist.add("aec");
		Operations.update(host, port, "key", newlist, timeout);
		ArrayList<String> update = Operations.read(host, port, "key");
		System.out.println("The value after update is" + update);
		Operations.delete(host, port, "key", timeout);
		ArrayList<String> delete = Operations.read(host, port, "key");
		System.out.println("The value after delete is" + delete);
		
	}
	
}
