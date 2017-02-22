package edu.gmu.swe622.lab4;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import edu.gmu.swe622.lab4.IHeartbeatServer;

public class HeartbeatClientMain {

	private static IHeartbeatServer heartbeatServer;

	public static void main(String[] args) {
		try {
			Registry registry = LocateRegistry.getRegistry("localhost", 9000);
			heartbeatServer = (IHeartbeatServer) registry.lookup(IHeartbeatServer.HEARTBEAT_SERVER_RMI_NAME);
		} catch (Exception e) {
			System.err.println("Client exception connecting to lock server: " + e.toString());

		}
		Scanner s = new Scanner(System.in);
		while(true)
		{
			System.out.println("Options: 1. Tell joke. 2. Quit (enter 1 or 2)");
			try{
				int opt = s.nextInt();
				if(opt == 2)
					break;
				else if(opt == 1)
				{
					System.out.println("OK, here's a joke:");
					System.out.println("Knock knock");
					System.out.println("Race condition");
					System.out.println("Who's there?");
				}
			}catch(InputMismatchException ex)
			{
				//nop
			}
		}
		s.close();
	}

}
