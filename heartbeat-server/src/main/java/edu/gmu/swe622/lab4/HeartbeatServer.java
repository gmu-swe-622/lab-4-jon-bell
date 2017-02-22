package edu.gmu.swe622.lab4;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.StampedLock;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import edu.gmu.swe622.lab4.IHeartbeatServer;

public class HeartbeatServer implements IHeartbeatServer {
	public HeartbeatServer() {
	}


	public static Registry createAndBind(int port) throws Exception {
		IHeartbeatServer lockServer = new HeartbeatServer();
		IHeartbeatServer stub = (IHeartbeatServer) UnicastRemoteObject.exportObject(lockServer, 0);
		Registry registry = LocateRegistry.createRegistry(port);
		registry.rebind(IHeartbeatServer.HEARTBEAT_SERVER_RMI_NAME, stub);
		System.out.println("Heartbeat bound to port " + port);
		return registry;
	}

	public static void main(String[] args) {
		Options options = new Options();
		int lockPort = 9000;
		try {
			Option lockPortOpt = Option.builder("port").hasArg().desc("Heartbeat server port (default 9000)").build();

			Option helpOpt = Option.builder("help").desc("Prints this message").build();
			options.addOption(lockPortOpt);
			options.addOption(helpOpt);

			CommandLine line = new DefaultParser().parse(options, args);
			if (line.hasOption("help")) {
				HelpFormatter formatter = new HelpFormatter();
				formatter.printHelp("Lab4HeartbeatServer", options);
				return;
			}
			if (line.hasOption("port"))
				lockPort = Integer.valueOf(line.getOptionValue("port"));
		} catch (ParseException | NumberFormatException ex) {
			ex.printStackTrace();
			return;
		}
		try {
			createAndBind(lockPort);
		} catch (Exception e) {
			System.err.println("Heartbeat server unable to bind");
			e.printStackTrace();
		}
	}


	@Override
	public int registerClient() throws RemoteException {
		// TODO Auto-generated method stub
		return 0;
	}


	@Override
	public void heartbeat(int client) throws RemoteException {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void disconnect(int client) throws RemoteException {
		// TODO Auto-generated method stub
		
	}
}
