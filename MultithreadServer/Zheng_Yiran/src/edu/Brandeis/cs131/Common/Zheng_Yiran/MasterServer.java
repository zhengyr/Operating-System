//Yiran Zheng
//zhengyr@brandeis.edu
//CS131
//the master server that manipulates all the basic server
package edu.Brandeis.cs131.Common.YourName;

import java.util.HashMap;
import java.util.LinkedList;

import edu.Brandeis.cs131.Common.Abstract.Client;
import edu.Brandeis.cs131.Common.Abstract.Log.Log;
import edu.Brandeis.cs131.Common.Abstract.Server;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class MasterServer extends Server {
	private final Map<Integer, List<Client>> mapQueues = new HashMap<Integer, List<Client>>();
	private final Map<Integer, Server> mapServers = new HashMap<Integer, Server>();

	public MasterServer(String name, Collection<Server> servers, Log log) {
		super(name, log);
		Iterator<Server> iter = servers.iterator();
		while (iter.hasNext()) {
			this.addServer(iter.next());
		}
	}

	public void addServer(Server server) {
		int location = mapQueues.size();
		this.mapServers.put(location, server);
		this.mapQueues.put(location, new LinkedList<Client>());
	}

	// connect to master server
	@Override
	public boolean connectInner(Client client) {
		boolean checkConnect = false;
		int key = getKey(client);
		synchronized (mapServers.get(key)) {
			if (mapQueues.get(key).isEmpty()) {//if wait list is empty, try to connect
				checkConnect = mapServers.get(key).connect(client);
				if(!checkConnect){//if fail to connect, add
					mapQueues.get(key).add(client);
				}
			} else {//if waitlist is not empty, then add
				mapQueues.get(key).add(client);
			}
			while (!checkConnect) {//wait first
				try {
					mapServers.get(key).wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}//if it is the first one
				if (client == mapQueues.get(key).get(0)) {
					checkConnect = mapServers.get(key).connect(client);
					if(checkConnect){
						mapQueues.get(key).remove(client);
						mapServers.get(key).notifyAll();
					}
				}
			}

		}
		return true;
	}

	// disconnect from server
	@Override
	public void disconnectInner(Client client) {
		// TODO Auto-generated method stub
		int key = getKey(client);
		Server s = mapServers.get(key);
		s.disconnect(client);// disconnect from the server and then notify all
								// client on the list
		synchronized (mapServers.get(key)) {
			mapServers.get(key).notifyAll();
		}

	}

	// returns a number from 0- mapServers.size -1
	// MUST be used when calling get() on mapServers or mapQueues
	private int getKey(Client client) {
		return client.getSpeed() % mapServers.size();
	}
}
