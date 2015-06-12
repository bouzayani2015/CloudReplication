package aec.replication;

import java.io.File;

public class XmlParser {
	
	public static void main(String argv[]) {

		try {
			
			File file = new File("src/resources/ReplicationPath.xml");
			Parsing parser = new Parsing();
			parser.ParseXML(file);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

}
