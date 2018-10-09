package mikirockerful.upm.zookeeper;

import java.nio.ByteBuffer;
import java.util.List;
import java.util.Random;

import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.ZooDefs.Ids;
import org.apache.zookeeper.data.Stat;


public class ConsistentCounter {
	
	static String[] hosts = {"127.0.0.1:2181", "127.0.0.1:2181", "127.0.0.1:2181"};
	static String counterPath = "/counter";
	static String lockPath = "/lock";
	private static final int SESSION_TIMEOUT = 2000;
	private ZooKeeper zk;
	private String lock;

	
	public ConsistentCounter() {
		//Choose a Zookeeper node
		Random rand = new Random();
		int i = rand.nextInt(hosts.length);
		//Create a session
		try {
			if (zk == null) {
				zk = new ZooKeeper(hosts[i], SESSION_TIMEOUT, new Watcher() {
					public void process (WatchedEvent e) {
						System.out.println("Session established");
						notify();
					}});
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
				//Create the directory structure
				String response = new String();
				//The counter node
				Stat s = zk.exists(counterPath, false);
				if (s == null) {
					response = zk.create(counterPath, longToBytes((long)0), Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
					System.out.println(response);
				}
				//The lock path
				Stat e = zk.exists(lockPath, false);
				if (e == null) {
					response = zk.create(lockPath, null, Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
					System.out.println(response);
				}
			} catch (KeeperException e) {
				System.out.println("The session with Zookeeper failed. Closing");
				return;
			} catch (InterruptedException e) {
				System.out.println("InterruptedException raised");
			}	
		}	
	}
	
	//Increment counter can only be called if we have the lock on the counter
	private void incrementCounter() {
		try {
			Stat s = zk.exists(counterPath, false);
			byte[] counterValue = zk.getData(counterPath, false, s);
			Long bi = bytesToLong(counterValue);
			++bi;
			counterValue = longToBytes(bi);
			zk.setData(counterPath, counterValue, -1);
		} catch (KeeperException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	public void lockAndIncrement() {
		this.lock();
		this.incrementCounter();
		try {
			this.releaseLock();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (KeeperException e) {
			e.printStackTrace();
		}
	}
	
	//Try to get the lock, and block execution until we get it
	public void lock() {
		try {
			String l = zk.create(lockPath + "/lock-", new byte[0], Ids.OPEN_ACL_UNSAFE,
					CreateMode.EPHEMERAL_SEQUENTIAL);
			this.lock = l.replace(lockPath + "/", "");
			final Object lock = new Object();
			synchronized (lock) {
				while (true) {
					List<String> lockList = zk.getChildren(lockPath, new Watcher() {
						public void process(WatchedEvent event) {
							synchronized (lock) {
								lock.notifyAll();
							}
						}
					});
					java.util.Collections.sort(lockList);
					if (this.lock.endsWith(lockList.get(0))) {
						return;
					} else {
						lock.wait();
					}

				}
			}
		} catch (KeeperException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}
	
	private void releaseLock() throws InterruptedException, KeeperException {
		zk.delete(lockPath+"/"+this.lock, -1);
	}
	
	public void printCounterValue() {
		try {
			Stat s = zk.exists(counterPath, false);
			byte[] counterValue = zk.getData(counterPath, false, s);
			Long bi = bytesToLong(counterValue);
			System.out.println(bi);
		} catch (Exception e) {
			System.out.println("Can't get value");
			System.out.println(e.toString());
		}
	}
	
	//Auxiliary functions to convert a "long" to/from a byte array
	static long bytesToLong(byte[] bytes) {
		ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
		buffer.put(bytes);
		buffer.flip();
		return buffer.getLong();
	}
	static byte[] longToBytes(long x) {
		ByteBuffer buffer = ByteBuffer.allocate(Long.BYTES);
		buffer.putLong(x);
		return buffer.array();
	}

	public static void main (String[] args) throws InterruptedException {
		ConsistentCounter cc = new ConsistentCounter();
		while(true) {
			cc.lockAndIncrement();
			cc.printCounterValue();
		}
	}
}
