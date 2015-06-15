/**
 *
 */
package aec.replication;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.Callable;

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

		int TimeOut = 1000;
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
		HashMap<String, List<String>> map = new HashMap<String, List<String>>();
		File file = new File("src/resources/ReplicationPath.xml");
		ParseXML parse = new ParseXML();
		try {
			parse.parseXML(file);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		map = parse.getMap();
		parse.readMap(map);
		
		// The key should be the path ID
		// We suppose that node A is the first node who received the update thus
		// the path ID is nodeA
		if (map.containsKey("nodeC")) {
			System.out.println("the map contains the key nodeC:");
			List<String> list = new ArrayList<String>();
			list = map.get("nodeC");
			int i = 0;
			while (i < list.size()) {
				// System.out.println("the first element in the map is :" +
				// list.get(1));
				if (list.get(i).equals("sync")) {
					String target = list.get(i + 1);
					System.out.println("the synch target is :" + target);
					// in our case target is nodeB we should be able to retieve
					// the IP address and the port number coressponding to nodeB
					// forward the create request to target server sychrounously
					Sender sender = new Sender("localhost", 6000);
					sender.sendMessage(req, TimeOut);
					i = i + 2;
					
				} else if (list.get(i).equals("async")) {
					String target = list.get(i + 1);
					System.out.println("the asynch target is :" + target);
					// forward the create request to target server
					// asynchronously
					Sender sender = new Sender("localhost", 6000);
					sender.sendMessageAsync(req, createHandler);
					i = i + 2;

				} else if (list.get(i).equals("quorum")) {
					i++;
					int qSize = Integer.valueOf(list.get(i));
					int j = i + 1;
					while (!list.get(j).equals("sync") && !list.get(j).equals("async") && !list.get(j).equals(null)) {
						String qparticipant = list.get(j);
						// forward quorum of size qSize to qparticipant
						// extarct the ipaddress and the port number
						// corresponding to the qparticipant
						System.out.println("I sent a quorum of size :" + qSize + "to q participant " + qparticipant);
						Sender sender = new Sender("localhost", 6000);
						sender.sendMessageAsync(req, createHandler);
						j++;

					}
					
				}
			}
		}
		
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
