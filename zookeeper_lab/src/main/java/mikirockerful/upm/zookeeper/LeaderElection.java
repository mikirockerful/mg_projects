package mikirockerful.upm.zookeeper;

import java.util.List;
import java.util.Random;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.data.Stat;


public class LeaderElection implements Watcher {

	static String[] hosts = {"127.0.0.1:2181", "127.0.0.1:2181", "127.0.0.1:2181"};
	private static String election = "/election";
	private static String aMember = "/member-";
	private static final int SESSION_TIMEOUT = 2000;
	private String myId;
	private String leader;
	private ZooKeeper zk;
	
	public LeaderElection () {
		//Choose a Zookeeper node
		Random rand = new Random();
		int i = rand.nextInt(hosts.length);
		//Create a session
		try {
			if (zk == null) {
				zk = new ZooKeeper(hosts[i], SESSION_TIMEOUT, cWatcher);
				try {
					wait();
				} catch (Exception e) {
				}
			}
		} catch (Exception e) {
			System.out.println("Error");
		}
		if (zk != null) {
			try {
				//Create the election directory. Only the first one will have to do this.
				String response = new String();
				Stat s = zk.exists(election, null);
				if (s == null) {
					response = zk.create(election, new byte[0], 
							Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
					System.out.println(response);
				}
				// Create a znode for registering as member and get my id
				myId = zk.create(election + aMember, new byte[0], 
						Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
				myId = myId.replace(election + "/", "");
				System.out.println("I am "+myId);
				this.leaderProcedure();
			} catch (KeeperException e) {
				System.out.println("The session with Zookeeper fails. Closing");
				return;
			} catch (InterruptedException e) {
				System.out.println("InterruptedException raised");
			}
		}
	}
	
	//Watcher for tracking state of the nodes
	public void process(WatchedEvent event) {
		try {
			Stat s = zk.exists(election, this);
			List<String> list = zk.getChildren(election, this, s);
			if (!list.contains(this.leader)){
				System.out.println("Leader has left. Executing leader procedure");
				this.leaderProcedure();
			}
		} catch (Exception e) {
			System.out.println("Exception: Something went wrong when watcher was triggered");
		}
	}
	
	// Watcher for tracking sessions
	private Watcher cWatcher = new Watcher() {
		public void process (WatchedEvent e) {
			System.out.println("Session established");
			notify();
		}
	};
	
	//Select a new leader
	private void leaderProcedure() {
		Stat s;
		try {
			s = zk.exists(election,this);
			List<String> list = zk.getChildren(election, this, s);
			//Sort the list of children
			java.util.Collections.sort(list);
			//The first one is the leader
			this.leader=list.get(0);
			if (myId.equals(leader)) {
				System.out.println("I am the leader, my id is "+myId);
			} else {
				System.out.println("The leader is "+leader);
			}
		} catch (KeeperException e) {
			System.out.println("The session with Zookeeper fails. Closing");
			return;
		} catch (InterruptedException e) {
			System.out.println("InterruptedException raised");
		}
	}
	
	
	public static void main(String args[]) {
		LeaderElection le=new LeaderElection();
		try {
			while (true) {
				Thread.sleep(20000); 			
				System.out.println("Periodic glory to our leader, he is "+le.leader);
			}
		} catch (Exception e) {
			System.out.println(e.toString());
		}
	}

}
