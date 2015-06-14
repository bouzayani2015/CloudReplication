package aec.replication;

import java.io.File;
import java.util.HashMap;
import java.util.List;

public class XmlParser {
	
	/**
	 * @param argv
	 */
	public static void main(String argv[]) {

		try {
			
			File file = new File("src/resources/ReplicationPath.xml");
			ParseXML parse = new ParseXML();
			parse.parseXML(file);
			HashMap<String, List<String>> map = new HashMap<String, List<String>>();
			map = parse.getMap();
			parse.readMap(map);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
	}

}
