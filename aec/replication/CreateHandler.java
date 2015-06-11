/**
 *
 */
package aec.replication;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import edu.kit.aifb.dbe.hermes.AsyncCallbackRecipient;
import edu.kit.aifb.dbe.hermes.IRequestHandler;
import edu.kit.aifb.dbe.hermes.Request;
import edu.kit.aifb.dbe.hermes.Response;
import edu.kit.aifb.dbe.hermes.Sender;

/**
 *
 *
 *
 */
public class CreateHandler
		implements IRequestHandler, AsyncCallbackRecipient {
	
	private static CreateHandler createHandler = new CreateHandler();

	@Override
	public Response handleRequest(Request req) {

		ReplicationPath path = new ReplicationPath();
		List<Serializable> items = new ArrayList<Serializable>();
		items = req.getItems();
		String key = (String) items.get(0);
		@SuppressWarnings("unchecked")
		ArrayList<String> value = (ArrayList<String>) items.get(1);
		System.out.println("Got " + value + " for key " + key);
		Storage.getInstance().create(key, value);
		Response resp = new Response(Storage.getInstance().read(key), "Result for create:", true, req);
		System.out.println("Result for create is :" + Storage.getInstance().read(key));
		// TODO Forward the request according to the replication path
		
		// forward the create request to another server
		Sender sender = new Sender("localhost", 6000);
		sender.sendMessageAsync(req, createHandler);

		return resp;
	}
	
	@Override
	public boolean requiresResponse() {
		return true;
	}
	
	@Override
	public boolean hasPriority() { // TODO Auto-generated method
		return false;
	}
	
	@Override
	public void callback(Response response) {
		if (response.responseCode()) {
			System.out.println("Successfull operation");
		}
	}
}
