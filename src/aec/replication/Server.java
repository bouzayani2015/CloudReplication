package aec.replication;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import edu.kit.aifb.dbe.hermes.Receiver;
import edu.kit.aifb.dbe.hermes.RequestHandlerRegistry;

public class Server {

	private static final Logger log = Logger.getLogger(Server.class);
	
	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args)
			throws Exception {
		PropertyConfigurator.configure("src/resources/log4j.properties");
		int port = 5888;
		
		RequestHandlerRegistry reg = RequestHandlerRegistry.getInstance();
		reg.registerHandler("delete", new HermesDeleteHandler());
		reg.registerHandler("get", new HermesGetHandler());
		reg.registerHandler("put", new HermesPutHandler());
		Receiver receiver = new Receiver(port);
		receiver.start();

	}
	
}
