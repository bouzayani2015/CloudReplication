import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import edu.kit.aifb.dbe.hermes.Request;
import edu.kit.aifb.dbe.hermes.Sender;

public class Client {

	private static final Logger log = Logger.getLogger(Client.class);
	
	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args)
			throws Exception {
		PropertyConfigurator.configure("src/resources/log4j.properties");
		Sender sender = new Sender("localhost", 5888);
		Request req = new Request(null, null);
		sender.sendMessage(req, 10000);
	}
}
